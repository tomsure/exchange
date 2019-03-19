package com.broctagon.marketserver.service;


import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
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
import com.broctagon.marketserver.model.Candle;
import com.broctagon.marketserver.model.CandleToWs;
import com.broctagon.marketserver.util.DateUtil;

@Service
public class HistoricalMarketService {
	
	// routingKey = marketServ
	@Value("${ws.routingKey.marketServToWs}")
	private String routingKey;
	
	@Value("${candle.timeframes}")
	private String timeframes;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;

	
	public Map<String, Candle> getTheLatestCandleFromRedis(String symbol, String[] timeframes) {
		Map<String, Candle> candles = new ConcurrentHashMap<>();
		redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection redisConnection = (StringRedisConnection) connection;
				for(String timeframe : timeframes) {
					String value = redisConnection.lIndex(symbol + Constant.SPLITTER + timeframe, 0);
					if(!StringUtils.isEmpty(value)) {
						candles.put(symbol + Constant.SPLITTER + timeframe, JSON.parseObject(value, Candle.class));
					}
				}
				return null;
			}
		});
		return candles;
	}

	/**
	 * 
	 * @param order  接收到的C++消息
	 * @param candlesFromRedis   所有的成交单记录（Map）
	 * @param timeframes  所有时间段 (数组)
	 * @return
	 */
	public Map<String, Candle> calculateCandles(Order order, Map<String, Candle> candlesFromRedis, String[] timeframes) {
		Map<String, Candle> candlesUpdate = new ConcurrentHashMap<>();
		//isEmpty() 判断容器中是否有元素  有（false）  没（true）
		if(candlesFromRedis.isEmpty()) {
			candlesUpdate = initHistoricalCandles(timeframes, order);
		}else {
			candlesUpdate = updateHistoricalCandles(candlesFromRedis, order);
		}
		return candlesUpdate;
	}

	/**
	 * 
	 * @param timeframes
	 * @param order
	 * @return
	 */
	private Map<String, Candle> initHistoricalCandles(String[] timeframes, Order order) {
		Map<String, Candle> candlesUpdate = new ConcurrentHashMap<>();
		for(String timeframe : timeframes) {
			Candle candle = getCandleFromOrder(order, timeframe);
			candlesUpdate.put(order.getSymbol() + Constant.SPLITTER + timeframe + Constant.SPLITTER + Constant.ADD_NEW_CANDLE, candle);
		}
		return candlesUpdate;
	}
	
	private Map<String, Candle> updateHistoricalCandles(Map<String, Candle> candlesFromRedis, Order order) {
		Map<String, Candle> candles = new ConcurrentHashMap<>();
		candlesFromRedis.keySet().parallelStream().forEach(key -> {
			Candle candle = candlesFromRedis.get(key);
			String timeframe = key.split(Constant.SPLITTER)[1];
			long currentTime = Long.parseLong(order.getTransactTime());
			//如果当前时间大于上一个蜡烛条时间，并小于上个蜡烛条的结束时间，就修改数据
			if(currentTime > candle.getStartTime().toEpochSecond(ZoneOffset.of("+8")) && currentTime < candle.getEndTime()) {
				double price = order.getDealPrice();
				//更新收盘价(已最后一单成交金额为收盘价)
				if(candle.getClose() != price) {
					candle.setClose(price);
					candles.put(key + Constant.SPLITTER + Constant.UPDATE_CURRENT_CANDLE, candle);
				}
				if(price > candle.getHigh()) {
					candle.setHigh(price);
					candles.put(key + Constant.SPLITTER + Constant.UPDATE_CURRENT_CANDLE, candle);
				}
				if(price < candle.getLow()) {
					candle.setLow(price);
					candles.put(key + Constant.SPLITTER + Constant.UPDATE_CURRENT_CANDLE, candle);
				}
			}else if(currentTime > candle.getEndTime()) {
				Candle newCandle = getCandleFromeOrderToUpdate(order, timeframe, candle);
				candles.put(key + Constant.SPLITTER + Constant.ADD_NEW_CANDLE, newCandle);
			}
		});
		return candles;
	}

	/**
	 *  创建Candle对象 存储成交单消息
	 * @param order
	 * @param timeframe
	 * @return
	 */
	private Candle getCandleFromOrder(Order order, String timeframe) {
		Candle candle = new Candle();
		candle.setSymbol(order.getSymbol());
		candle.setOpen(order.getDealPrice());
		candle.setHigh(order.getDealPrice());
		candle.setLow(order.getDealPrice());
		candle.setClose(order.getDealPrice());
		candle.setStartTime(DateUtil.getCurrentDateTime(DateUtil.getTimeByTimeframe(order.getTransactTime(), timeframe, 0)));
		candle.setEndTime(DateUtil.getNextTimeByTimeframe(order.getTransactTime(), timeframe));
		return candle;
	}
	
	private Candle getCandleFromeOrderToUpdate(Order order, String timeframe, Candle candle) {
		Candle newcandle = new Candle();
		newcandle.setSymbol(order.getSymbol());
		newcandle.setOpen(candle.getClose());
		newcandle.setHigh(order.getDealPrice());
		newcandle.setLow(order.getDealPrice());
		newcandle.setClose(order.getDealPrice());
		newcandle.setStartTime(DateUtil.getCurrentDateTime(DateUtil.getTimeByTimeframe(order.getTransactTime(), timeframe, 0)));
		newcandle.setEndTime(DateUtil.getNextTimeByTimeframe(order.getTransactTime(), timeframe));
		return newcandle;
	}

	public void saveCandles(Map<String, Candle> updateCandles) {
		if(!updateCandles.isEmpty()) {
			redisTemplate.execute(new RedisCallback<String>() {

				@Override
				public String doInRedis(RedisConnection connection) throws DataAccessException {
					StringRedisConnection redisConnection = (StringRedisConnection) connection;
					for(Entry<String, Candle> entry : updateCandles.entrySet()) {
						int index = entry.getKey().lastIndexOf(Constant.SPLITTER);
						String field = entry.getKey().substring(0, index);
						String option = entry.getKey().substring(index + 1, entry.getKey().length());
						if(Constant.UPDATE_CURRENT_CANDLE.equals(option)){
							//Redis Lset 通过索引来设置元素的值。
							redisConnection.lSet(field, 0, JSON.toJSONString(entry.getValue()));
						}else if(Constant.ADD_NEW_CANDLE.equals(option)) {
							redisConnection.lPush(field, JSON.toJSONString(entry.getValue()));
						}
					}
					return null;
				}
				
			});
		}
	}

	public void sendToWs(Map<String, Candle> updateCandles) {
		if(!updateCandles.isEmpty()) {
			updateCandles.entrySet().parallelStream().forEach(entry -> {
				CandleToWs candleToWs = new CandleToWs();
				candleToWs.setTimeframe(entry.getKey().split("_")[1]);
				List<Candle> candles = new ArrayList<>();
				candles.add(entry.getValue());
				candleToWs.setMarketHistory(candles);
				rabbitTemplate.convertAndSend(routingKey, JSON.toJSONString(candleToWs));
			});
		}
	}
  
//	public void init() {
//		Candle candle = new Candle();
//		Random random = new Random(47);
//		Set<String> symbols = new HashSet<>(); 
//		symbols.add("RKT/BTC");
//		symbols.add("ETH/BTC");
//		symbols.add("LTC/BTC");
//		symbols.add("EOS/ETH");
//		symbols.add("NXS/ETH");
//		symbols.add("XRB/ETH");
//		String[] timeframeArr = timeframes.split(",");
//		long currentTime = Clock.systemUTC().millis() / 1000;
//		redisTemplate.execute(new RedisCallback<String>() {
//
//			@Override
//			public String doInRedis(RedisConnection connection) throws DataAccessException {
//				StringRedisConnection redisConnection = (StringRedisConnection) connection;
//				for(int i = 99; i > 0; i --) {
//					for(String symbol : symbols) {
//						for(String timeframe : timeframeArr) {
//							candle.setClose(4000 + random.nextInt(i) + random.nextFloat() * (1+i));
//							candle.setOpen(4000 + random.nextInt(i) + random.nextFloat() * (i+1));
//							candle.setLow(candle.getClose() > candle.getHigh() ?  candle.getHigh() - random.nextDouble() : candle.getLow() - random.nextDouble());
//							candle.setHigh(candle.getClose() > candle.getHigh() ? candle.getClose() + random.nextDouble() : candle.getOpen() + random.nextDouble());
//							candle.setSymbol(symbol);
//							candle.setStartTime(DateUtil.getLastTimeByTimeframe(currentTime, timeframe, i));
//							candle.setEndTime(DateUtil.getNextTimeByTimeframe(String.valueOf(candle.getStartTime().toEpochSecond(ZoneOffset.UTC)), timeframe));
//							redisConnection.lPush(symbol + "_" + timeframe, JSON.toJSONString(candle));
//						}
//					}
//				}
//				return null;
//			}
//		});
//	}
	
}
