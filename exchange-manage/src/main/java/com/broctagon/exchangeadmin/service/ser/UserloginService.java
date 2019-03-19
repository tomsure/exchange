package com.broctagon.exchangeadmin.service.ser;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.broctagon.exchangeadmin.constant.Constant;
import com.broctagon.exchangeadmin.model.User;
import com.broctagon.exchangeadmin.model.UserOperation.LoginService;
import com.broctagon.exchangeadmin.model.UserOperation.PwdModificationService;
import com.broctagon.exchangeadmin.model.UserOperation.RegisterService;
import com.broctagon.exchangeadmin.model.UserOperation.StatusAll;

@Service
public class UserloginService {
	
	@Value("${id}")
	private String id;
	
	//routingKey = WebSrv.
	@Value("${mq.routingKey.userlogin}")
	private String routingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	/**
	 * 登录回显消息发送
	 * @param message 回显的信息
	 */
	public void SendloginstatusSer(String wsId,String requestId,String sessionId,User user) {
		LoginService loginService = new LoginService(wsId,requestId,sessionId,user.getStatus(), user.getID(),user.getKickSessionId());
		System.err.println(Constant.GSON.toJson(loginService));
		//routingKey = WebSrv
		//第一个参数表示routingkey，第二个参数即消息。
		rabbitTemplate.convertAndSend(routingKey + id,Constant.GSON.toJson(loginService));
	}
	
	
	/**
	 * 邮箱验证码回显发送
	 * @param status
	 * @param SessionID
	 */
	public void SenduserexistSer(int status,String SessionID) {
		StatusAll statusall = new StatusAll(status,SessionID);
		System.err.println(Constant.GSON.toJson(statusall));
		rabbitTemplate.convertAndSend(routingKey + id,Constant.GSON.toJson(statusall));
	}
	
	/**
	 * 注册回显消息发送
	 */
	public void RegisterSer(String sessionId,int userId) {
		RegisterService sRegisterService =new RegisterService(userId,sessionId,"0");
		System.err.println(Constant.GSON.toJson(sRegisterService));
		rabbitTemplate.convertAndSend(routingKey + id,Constant.GSON.toJson(sRegisterService));
	}
	
	/**
	 * 忘记密码回显消息发送PwdModification
	 */
	public void PwdModificationSer(String sessionId,int status) {
		PwdModificationService pwdModificationService = new PwdModificationService(sessionId, status);
		System.err.println(Constant.GSON.toJson(pwdModificationService));
		rabbitTemplate.convertAndSend(routingKey + id,Constant.GSON.toJson(pwdModificationService));
	}
	
}
