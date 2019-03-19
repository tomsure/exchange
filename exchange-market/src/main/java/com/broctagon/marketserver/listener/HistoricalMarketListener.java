package com.broctagon.marketserver.listener;


import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.broctagon.marketserver.constant.Tag;
import com.broctagon.marketserver.message.Order;
import com.broctagon.marketserver.model.Candle;
import com.broctagon.marketserver.service.HistoricalMarketService;
import com.broctagon.marketserver.util.MessageUtil;

@Service
public class HistoricalMarketListener {
	
	@Value("${candle.timeframes}")
	private String timeframes;
	
	@Autowired
	private HistoricalMarketService orderService;
	
	/**
	 * 
	 * @param rabbitMessage
	 */
	@RabbitListener(queues="HISTORICAL_MARKET")
	public void receiveMessage(Message rabbitMessage){
		String message = new String(rabbitMessage.getBody());
		String tag = MessageUtil.getTag(message);
		if(Integer.parseInt(tag) == Tag.ORDER) {
			Order order = JSON.parseObject(message, Order.class);
			String[] timeframeArr = timeframes.split(",");
			//获得所有时间段历史成交单的最近一单数据
			Map<String, Candle> candlesFromRedis = orderService.getTheLatestCandleFromRedis(order.getSymbol(), timeframeArr);
			Map<String, Candle> updateCandles = orderService.calculateCandles(order, candlesFromRedis, timeframeArr);
			orderService.saveCandles(updateCandles);
			orderService.sendToWs(updateCandles);
		}
	}
	
	
}
