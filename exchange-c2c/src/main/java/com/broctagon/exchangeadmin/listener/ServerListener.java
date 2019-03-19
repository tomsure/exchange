package com.broctagon.exchangeadmin.listener;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ServerListener {
	
	@Autowired
	private MessageDispatcher messageDispatcher;
	 
	@RabbitListener(queues="key_c2c_manager_res")
	public void listener(Message rabbitMessage) {
		String message = new String(rabbitMessage.getBody());
		System.out.println("key_c2c_manager_res message：" +message);
		
		messageDispatcher.dispatchManagerRes(message);
		
		
	}
}
