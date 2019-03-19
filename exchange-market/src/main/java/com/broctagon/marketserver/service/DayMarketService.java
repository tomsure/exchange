package com.broctagon.marketserver.service;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.broctagon.marketserver.constant.Constant;
import com.broctagon.marketserver.message.Order;
import com.broctagon.marketserver.model.DayMarket;
import com.broctagon.marketserver.model.DayMarketToWs;
import com.broctagon.marketserver.util.DateUtil;

@Service	
public class DayMarketService {
	
	// routingKey = marketServ
	@Value("${ws.routingKey.marketServToWs}")
	private String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * 去redis中拿到最新今日行情数据
	 * @param symbol
	 * @return
	 */
	public DayMarket getDayMarketFromRedis(String symbol) {
		DayMarket dayMarket = null;
		//redisTemplate.opsForHash().entries(symbol)  返回map集合    .get(Constant.TODAY_MARKET)  获取具体值  转String
		String marketStr = String.valueOf(redisTemplate.opsForHash().entries(symbol).get(Constant.TODAY_MARKET));
		if(!StringUtils.isEmpty(marketStr)) {
			//如果不为空转为一个对象
			dayMarket = JSON.parseObject(marketStr, DayMarket.class);
		}
		return dayMarket;
	}

	/**
	 * 更新或新建今日行情和昨日行情、
	 * @param dayMarketFromRedis
	 * @param order
	 * @return
	 */
	public Map<String, DayMarket> updateDayMarket(DayMarket dayMarketFromRedis, Order order) {
		Map<String, DayMarket> dayMarkets = new ConcurrentHashMap<>();
		if(dayMarketFromRedis == null) {
			//给DayMarket赋值
			DayMarket dayMarket = initDayMarket(order);
			dayMarkets.put(order.getSymbol() + Constant.SPLITTER + Constant.TODAY_MARKET, dayMarket);
		//如果当前下单时间小于从redis拿到的消息的时间+1天
		}else if(Long.parseLong(order.getTransactTime()) < DateUtil.getNextDay(dayMarketFromRedis.getTime())){
			//把最新下单交易数据更新到今日行情数据
			dayMarketFromRedis.setCurrent(order.getDealPrice());
			dayMarketFromRedis.setVolume(dayMarketFromRedis.getVolume() + order.getDealNum());
			if(dayMarketFromRedis.getHigh() < order.getDealPrice()) {
				dayMarketFromRedis.setHigh(order.getDealPrice());
			}
			if(dayMarketFromRedis.getLow() > order.getDealPrice()) {
				dayMarketFromRedis.setLow(order.getDealPrice());
			}
			dayMarkets.put(order.getSymbol() + Constant.SPLITTER + Constant.TODAY_MARKET, dayMarketFromRedis);
			//如果当前下单时间大于等于从redis拿到的消息的时间+1天
		}else if(Long.parseLong(order.getTransactTime()) >= DateUtil.getNextDay(dayMarketFromRedis.getTime())) {
			//新建蜡烛条数据，拿到的昨日行情数据替代到今天的昨日数据
			DayMarket dayMarket = initDayMarketNnw(order,dayMarketFromRedis);
			dayMarkets.put(order.getSymbol() + Constant.SPLITTER + Constant.TODAY_MARKET, dayMarket);
			dayMarkets.put(order.getSymbol() + Constant.SPLITTER + Constant.YESTERDAY_MARKET, dayMarketFromRedis);
		}
		return dayMarkets;
	}

	/**
	 * 把Order对象的值给DayMarket对象赋值
	 * 每种交易币第一次下单创建的方法
	 * @param order
	 * @return
	 */
	private DayMarket initDayMarket(Order order) {
		DayMarket dayMarket = new DayMarket();
		dayMarket.setHigh(order.getDealPrice());
		dayMarket.setLow(order.getDealPrice());
		dayMarket.setOpen(order.getDealPrice());
		dayMarket.setCurrent(order.getDealPrice());
		dayMarket.setVolume(order.getDealNum());
		dayMarket.setTime((DateUtil.getCurrentDateTime(Long.parseLong(order.getTransactTime()))).withHour(0).withMinute(0).withSecond(0));
		return dayMarket;
	}
	
	/**
	 * 每天新的行情创建
	 * @param order
	 * @return
	 */
	private DayMarket initDayMarketNnw(Order order, DayMarket dayMarketFromRedis) {
		DayMarket dayMarket = new DayMarket();
		dayMarket.setHigh(order.getDealPrice());
		dayMarket.setLow(order.getDealPrice());
		dayMarket.setOpen(dayMarketFromRedis.getCurrent());
		dayMarket.setCurrent(order.getDealPrice());
		dayMarket.setVolume(order.getDealNum());
		dayMarket.setTime((DateUtil.getCurrentDateTime(Long.parseLong(order.getTransactTime()))).withHour(0).withMinute(0).withSecond(0));
		return dayMarket;
	}

	/**
	 * 传进最新数据，操作redis执行更新数据
	 * @param updateDayMarket
	 */
	public void saveMarket(Map<String, DayMarket> updateDayMarket) {
		redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection redisConnection = (StringRedisConnection) connection;
				for(Entry<String, DayMarket> entry : updateDayMarket.entrySet()) {
					//获取key  (通过截取前半部分)
					String key = entry.getKey().split(Constant.SPLITTER)[0];
					//获取hash中的键  (通过截取后半部分)
					String field = entry.getKey().split(Constant.SPLITTER)[1];
					//存进redis
					redisConnection.hSet(key, field, JSON.toJSONString(entry.getValue()));
				}
				return null;
			}
		});
	}

	/**
	 * 推送最新数据
	 * @param updateDayMarket
	 */
	public void sendToWs(Map<String, DayMarket> updateDayMarket) {
		DayMarketToWs dayMarketToWs = new DayMarketToWs();
		for(Entry<String, DayMarket> entry : updateDayMarket.entrySet()) {
			dayMarketToWs.setSymbol(entry.getKey().split("_")[0]);
			if(entry.getKey().split("_")[1].contains(Constant.YESTERDAY_MARKET)) {
				dayMarketToWs.setYesterday(entry.getValue());
			}else {
				dayMarketToWs.setToday(entry.getValue());
			}
		}
		rabbitTemplate.convertAndSend(routingKey, JSON.toJSONString(dayMarketToWs));
	}


}
