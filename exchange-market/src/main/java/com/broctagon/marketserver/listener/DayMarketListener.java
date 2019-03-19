package com.broctagon.marketserver.listener;


import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.broctagon.marketserver.constant.Tag;
import com.broctagon.marketserver.message.Order;
import com.broctagon.marketserver.model.DayMarket;
import com.broctagon.marketserver.service.DayMarketService;
import com.broctagon.marketserver.util.MessageUtil;

@Service
public class DayMarketListener {
	
	@Autowired
	private DayMarketService dayMarketService;
	
	@RabbitListener(queues="DAY_MARKET")
	public void receiveMessage(Message rabbitMessage){
		String message = new String(rabbitMessage.getBody());
		String tag = MessageUtil.getTag(message);
		if(Integer.parseInt(tag) == Tag.ORDER) {
			//JSON.parseObject，是将Json字符串转化为相应的对象；JSON.toJSONString则是将对象转化为Json字符串。
			//在JSON.parseObject 的时候，会去填充名称相同的属性。对于Json字符串中没有，而model类有的属性，会为null；对于model类没有，而Json字符串有的属性，不做任何处理。
			Order order = JSON.parseObject(message, Order.class);
			//从redis中拿到today的信息
			DayMarket dayMarketFromRedis = dayMarketService.getDayMarketFromRedis(order.getSymbol());
			Map<String, DayMarket> updateDayMarket = dayMarketService.updateDayMarket(dayMarketFromRedis, order);
			dayMarketService.saveMarket(updateDayMarket);
			dayMarketService.sendToWs(updateDayMarket);
		}
	}
	
}
