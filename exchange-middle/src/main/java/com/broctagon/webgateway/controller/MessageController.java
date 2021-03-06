package com.broctagon.webgateway.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.model.util.EmailSendRequest;
import com.broctagon.webgateway.service.ManageServService;
import com.broctagon.webgateway.util.MessageUtil;
import com.broctagon.webgateway.util.RandomCode;

@RestController
public class MessageController {
	
	@Value("${id}")
	private String wsId;
	
	// routingKey = key.security.email.req
	@Value("${mq.routingKey.manageServ}")
	private String routingKey;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@Autowired
	private ManageServService manageServService;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 找回密码发送邮箱验证码请求、、注册获取邮箱验证码求情
	 * @param message
	 * @param sessionId
	 */
	@MessageMapping("/message/sendEmail")
	public void sendEmail(String message, @Header("simpSessionId") String sessionId) {
		//生成邮箱验证码
		String randonCode = RandomCode.getRandomCode();
		String email = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
		String Status = MessageUtil.getContentFromMsg(message, Constant.STATUS);
		EmailSendRequest emailSendRequest = new EmailSendRequest();
		emailSendRequest.setRequestID(MessageUtil.getRequestId(message));
		emailSendRequest.setDest(email);
		emailSendRequest.setCode(randonCode);
		emailSendRequest.setwSID(Integer.parseInt(wsId));
		emailSendRequest.setSessionID(sessionId);
		emailSendRequest.setStatus(Status);
		//保存email和邮箱验证码
		Constant.emailMaps.put(email, randonCode); 
		System.err.println("EmailSendRequest对象,求情发送验证码,Tag=69636:"+Constant.GSON.toJson(emailSendRequest));
		rabbitTemplate.convertAndSend(routingKey, Constant.GSON.toJson(emailSendRequest));
	}
	
	@MessageMapping("/message/sendSms")
	public void sendSms(String message, @Header("simpSessionId") String sessionId) {
		manageServService.sendMessageToManagerSer(messageUtil.addTitleToMsg(message, sessionId));
	}

}
