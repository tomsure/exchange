package com.broctagon.exchangeadmin.listener;



import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class C2cServerListener {
	
	private static Logger logger = Logger.getLogger(C2cServerListener.class);
	
	@Autowired
	private MessageDispatcher messageDispatcher;
	 
	@RabbitListener(queues="key.c2c.req")
	public void listener(Message rabbitMessage) {
		String message = new String(rabbitMessage.getBody());
		logger.info("exchange c2c 请求消息体:" +message);
		
		messageDispatcher.response(message);
	}
}
