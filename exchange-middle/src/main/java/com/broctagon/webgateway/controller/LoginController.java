package com.broctagon.webgateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.constant.Path;
import com.broctagon.webgateway.model.manage.ForgetLoginPwdRequest;
import com.broctagon.webgateway.model.manage.RegisterRequest;
import com.broctagon.webgateway.service.ManageServService;
import com.broctagon.webgateway.service.PushService;
import com.broctagon.webgateway.util.MessageUtil;

@RestController
public class LoginController {
	
	@Autowired
	private MessageUtil messageUtil;

	@Autowired
	private ManageServService manageServService;

	@Autowired
	private SimpMessageSendingOperations messageSender;		

	//user
	/**
	 * 踢出用户求情，执行广播消息
	 * @param message
	 * @param sessionId
	 * @return
	 */
	@MessageMapping("/user/kick")
	@SendTo("/gateway/kick")
	public String kick(String message, @Header("simpSessionId") String sessionId) {
		System.out.println("踢出登录++++++++++++++++++++++++++++++++"+message);
		System.err.println(messageUtil.addTitleToMsg(message, sessionId));
		return message;
	}
	
	/**
	 * 用户登录
	 * @param message
	 * @param sessionId
	 */
	@MessageMapping("/user/login")
	public void login(String message, @Header("simpSessionId") String sessionId) {
		System.err.println(messageUtil.addTitleToMsg(message, sessionId));
		manageServService.sendMessageToManagerSer(messageUtil.addTitleToMsg(message, sessionId));
	}
	
	/**
	 * 登出
	 * @param message
	 * @param sessionId
	 */
	@MessageMapping("/user/logout")
	public void logout(String message, @Header("simpSessionId") String sessionId) {
		System.err.println(message);
		PushService.removeSessionId(sessionId);
		manageServService.sendMessageToManagerSer(messageUtil.addTitleToMsg(message, sessionId));
	}
	
	
	/**
	 * 注册请求
	 * @param message
	 * @param sessionId
	 */
	@MessageMapping("/user/registry")
	public void registry(String message, @Header("simpSessionId") String sessionId) {
		String email = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
		String emailCode = MessageUtil.getContentFromMsg(message, Constant.EMAIL_CODE);
		//获取登录邮箱
 		if(emailCode.equals(Constant.emailMaps.get(email))) {
			RegisterRequest registerRequest = new RegisterRequest(Integer.parseInt(MessageUtil.getTag(message)), email, MessageUtil.getContentFromMsg(message, "Password"), sessionId);
			manageServService.sendMessageToManagerSer(messageUtil.addTitleToMsg(Constant.GSON.toJson(registerRequest), sessionId));
		}else {
			messageSender.convertAndSend(Path.PREFIX + Path.REGISTRY + sessionId, "{\"Tag\":65553, \"UserID\":10026, \"SessionID\":\"" + sessionId + "\",\"Status\":\"-100\"}");
		}
	}
	
	/**
	 * 忘记密码请求拦截
	 * @param message
	 * @param sessionId
	 */
	@MessageMapping("/user/resetPwd")
	public void resetPwd(String message, @Header("simpSessionId") String sessionId) {
		System.out.println(message);
		String email = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
		String emailCode = MessageUtil.getContentFromMsg(message, Constant.EMAIL_CODE);
		String requestID = MessageUtil.getContentFromMsg(message, Constant.REQUEST_ID);
		if(emailCode.equals(Constant.emailMaps.get(email))) {
			ForgetLoginPwdRequest forgetLoginPwdRequest = new ForgetLoginPwdRequest();
			forgetLoginPwdRequest.setEMail(email);
			forgetLoginPwdRequest.setRequestID(requestID);
			forgetLoginPwdRequest.setPassword(MessageUtil.getContentFromMsg(message, "Password"));
			System.out.println("忘记密码请求"+Constant.GSON.toJson(forgetLoginPwdRequest));
			manageServService.sendMessageToManagerSer(messageUtil.addTitleToMsg(Constant.GSON.toJson(forgetLoginPwdRequest), sessionId));
		}else {
			messageSender.convertAndSend(Path.PREFIX + Path.FORGET_PWD + sessionId, "{\"Tag\":147457,\"SessionID\":\"" + sessionId + "\",\"Status\":-100}");
		}
	}
	
}
