package com.broctagon.webgateway.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import com.broctagon.webgateway.constant.Path;
import com.broctagon.webgateway.constant.Tag;
import com.broctagon.webgateway.service.PushService;
import com.broctagon.webgateway.util.MessageUtil;

@Service
public class C2CServListener {
	
	@Autowired
	private SimpMessageSendingOperations messageSender;

	@RabbitListener(queues="C2C.ANS." + "${id}")
	public void listener(Message rabbitMessage) {
		String message = new String(rabbitMessage.getBody());
		System.err.println("C2C Recieved: " + message);
		String tag = MessageUtil.getTag(message);
		String sessionId = MessageUtil.getSessionId(message);
		switch (Integer.parseInt(tag)) {
			case Tag.C2C_OPEN_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_OPEN_ORDER + sessionId, message);
				break;
			case Tag.C2C_CANCEL_OPEN_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_CANCEL_OPEN_ORDER + sessionId, message);
				break;
			case Tag.C2C_GET_OPEN_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_GET_OPEN_ORDER + sessionId, message);
				break;
			case Tag.C2C_GET_USER_PENDING_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_GET_USER_PENDING_ORDER + sessionId, message);
				break;
			case Tag.C2C_GET_USER_OPEN_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_GET_USER_OPEN_ORDER + sessionId, message);
				break;
			case Tag.C2C_GET_USER_HISTORICAL_ORDER_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_GET_USER_HISTORICAL_ORDER + sessionId, message);
				break;
			case Tag.C2C_ENTRUST_RESPONSE:
				System.err.println(message);
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_ENTRUST + sessionId, message);
				break;
			case Tag.C2C_TRADE_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_TRADE + sessionId, message);
				break;
			case Tag.C2C_CANCEL_TRADE_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_CANCEL_TRADE + sessionId, message);
				break;
			case Tag.C2C_PAY_RESPONSE:
				System.out.println("aa"+message);
				String userId = MessageUtil.getContentFromMsg(message,"UserId");
				if(userId != null){
					System.out.println("最终获取+"+PushService.selectSessionId(userId));;
					if(PushService.selectSessionId(userId) != null){
						messageSender.convertAndSend(Path.PREFIX + Path.CONFIRMATION_PROMPT + PushService.selectSessionId(userId), message);
					}
				}
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_PAY + sessionId, message);
				break;
			case Tag.C2C_PAY_CONFIRM_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_PAY_CONFIRM + sessionId, message);
				break;
			case Tag.C2C_COIN_LIST_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_COIN_LIST + sessionId, message);
				break;
			case Tag.C2C_COIN_MARKET_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_COIN_MARKET + sessionId, message);
				break;
			case Tag.C2C_COIN_HISTORICAL_MARKET_RESPONSE:
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_COIN_HISTORICAL_MARKET + sessionId, message);
				break;
			case Tag.C2C_USER_ASSETS_RES:
				
				String userID = MessageUtil.getContentFromMsg(message,"userId");
				if(userID != null){
					System.out.println("最终获取+"+PushService.selectSessionId(userID));;
					if(PushService.selectSessionId(userID) != null){
						messageSender.convertAndSend(Path.PREFIX + Path.C2C_USER_ASSETS_RES + PushService.selectSessionId(userID), message);
					}
				}
				
				messageSender.convertAndSend(Path.PREFIX + Path.C2C_USER_ASSETS_RES + sessionId, message);
				break;
			case Tag.C2C_COIN_TRADING_STATUS_RESPONSE:
				System.out.println(message);
				String toUserId = MessageUtil.getContentFromMsg(message,"ToUserId");
				if(toUserId != null){
					String userSessionId = PushService.selectSessionId(toUserId);
					System.out.println("最终获取+"+userSessionId);
					if(PushService.selectSessionId(toUserId) != null){
						messageSender.convertAndSend(Path.PREFIX + Path.C2C_TRADINGSTATUS + userSessionId, message);
					}
				}
				break;
			default:
				break;
		}
	}
}
