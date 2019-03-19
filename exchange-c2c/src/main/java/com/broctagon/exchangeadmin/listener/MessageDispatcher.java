package com.broctagon.exchangeadmin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.broctagon.exchangeadmin.constant.RabbitmqConstants;
import com.broctagon.exchangeadmin.message.BaseMsg;
import com.broctagon.exchangeadmin.message.C2cResultRes;
import com.broctagon.exchangeadmin.message.ManagerFreezeRes;
import com.broctagon.exchangeadmin.service.C2cCoinService;
import com.broctagon.exchangeadmin.service.C2cEntrustService;
import com.broctagon.exchangeadmin.service.C2cTradeService;
import com.broctagon.exchangeadmin.util.PascalNameFilter;



@Component
public class MessageDispatcher {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private C2cCoinService c2cCoinService;
	
	@Autowired
	private C2cEntrustService c2cEntrustService;
	
	@Autowired
	private C2cTradeService c2cTradeService;
	
	public void response(String req){	
		logger.info("c2c request content:" + req);
		
		BaseMsg baseMsgReq = JSON.parseObject(req, BaseMsg.class);
		BaseMsg baseMsgRes = null;
		
		int tag = baseMsgReq.getTag();
		switch(tag){
			
			case RabbitmqConstants.TAG_ENTRUST_REQ: //挂单买单  0
				baseMsgRes = c2cEntrustService.addEntrust(req);
				break;	
			case RabbitmqConstants.TAG_ENTRUST_CANCEL_REQ://撤单  status : 1
				baseMsgRes = c2cEntrustService.cancelEntrust(req);
				break;
			case RabbitmqConstants.TAG_QUERY_ENTRUST_LIST_REQ://买和卖的五条数据
				baseMsgRes = c2cEntrustService.queryEntrustList(req);
				break;
			case RabbitmqConstants.TAG_QUERY_USER_ENTRUST_LIST_REQ://用户待定订单列表
				baseMsgRes = c2cEntrustService.queryUserEntrust(req);
				break;			
				
			case RabbitmqConstants.TAG_TRADE_REQ: //买入
				baseMsgRes = c2cTradeService.trade(req);
				break;	
			case RabbitmqConstants.TAG_TRADE_CANCEL_REQ://买方交易超时  //1
				baseMsgRes = c2cTradeService.cancelTrade(req);
				break;			
			case RabbitmqConstants.TAG_TRADE_CONFORM_PAY_REQ:
				baseMsgRes = c2cTradeService.confirmPay(req);//status : 2
				break;	
			case RabbitmqConstants.TAG_TRADE_CONFORM_RECEIVE_REQ:
				baseMsgRes = c2cTradeService.confirmReceived(req);//status : 3
				break;					
			case RabbitmqConstants.TAG_TRADE_HISTORYMARKET_REQ://三十条已成交的记录
				baseMsgRes = c2cTradeService.findHistoryMarket(req);
				break;
			case RabbitmqConstants.TAG_QUERY_USER_HISTORYTRADE_LIST_REQ://用戶已成交列表
				baseMsgRes = c2cTradeService.findUserHistoryTrade(req);
				break;
			case RabbitmqConstants.TAG_QUERY_USER_CURRENT_TRADE_REQ://用户挂单列表
				baseMsgRes = c2cTradeService.findCurrentTrade(req);
				break;				
			case RabbitmqConstants.TAG_TRADE_MARKET_REQ://昨日行情今日行情，最新成交价
				baseMsgRes = c2cTradeService.findTradeMarket(req);
				break;
				
			case RabbitmqConstants.TAG_COIN_LIST_REQ://货币种类
				baseMsgRes = c2cCoinService.findC2cCoinList();
				break;
				
			default: 
				baseMsgRes = unkownTag(baseMsgReq);
				break;
		}
		
		baseMsgRes.setRequestID(baseMsgReq.getRequestID());
		baseMsgRes.setSessionID(baseMsgReq.getSessionID());
		baseMsgRes.setWSID(baseMsgReq.getWSID());
		baseMsgRes.setTag(baseMsgReq.getTag()+1);
		String res = JSON.toJSONString(baseMsgRes, new PascalNameFilter());
		MessageProperties properties = new MessageProperties();
		properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		Message resMessage = new Message(res.getBytes(), properties);
		rabbitTemplate.send(RabbitmqConstants.MQ_KEY_C2C_RES + baseMsgReq.getWSID(), resMessage);
		
		logger.info("c2c response content:" + res);
	}
	
	public BaseMsg unkownTag(BaseMsg baseMsgReq){
		C2cResultRes c2cResultRes = new C2cResultRes(); 
		c2cResultRes.setResult(false);
		c2cResultRes.setReason("Unknown Tag: " + baseMsgReq.getTag());
		return c2cResultRes;
	}
	
	public void sendToManager(String req){
		logger.info("sendToManager content:" + req);
		
		MessageProperties properties = new MessageProperties();
		properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		Message resMessage = new Message(req.getBytes(), properties);
		rabbitTemplate.send(RabbitmqConstants.MQ_KEY_MANAGER_REQ, resMessage);
	}
	
	public void dispatchManagerRes(String res){
		logger.info("c2c manager response content:" + res);
		
		ManagerFreezeRes baseMsgReq = JSON.parseObject(res, ManagerFreezeRes.class);
		
		int tag = baseMsgReq.getTag();
		switch(tag){		
			case RabbitmqConstants.TAG_MANAGER_FREEZE_RES:
				c2cEntrustService.freezonRes(res);
			break;
			
			default: break;
		}
		
	}
	

	// below is used for test
	public void sendReq(){
		String req = "req yes";
		MessageProperties properties = new MessageProperties();
		properties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		Message resMessage = new Message(req.getBytes(), properties);
		rabbitTemplate.send(RabbitmqConstants.MQ_KEY_C2C_REQ, resMessage);
	}
	
	
}
