package com.broctagon.exchangeadmin.listener;



import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.broctagon.exchangeadmin.constant.Constant;
import com.broctagon.exchangeadmin.constant.Tag;
import com.broctagon.exchangeadmin.model.User;
import com.broctagon.exchangeadmin.model.UserOperation.RegisterService;
import com.broctagon.exchangeadmin.service.EmailService;
import com.broctagon.exchangeadmin.service.UserSystemService;
import com.broctagon.exchangeadmin.service.ser.UserloginService;
import com.broctagon.exchangeadmin.util.MessageUtil;
import com.broctagon.exchangeadmin.util.RedisUtil;




@Controller
public class LoginsServListener {
	
	@Autowired
	private UserSystemService userSystemService;
	
	@Autowired
	private UserloginService userloginService;

	@Autowired
	private RedisUtil redisutil;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * 获取middle工程的登录请求mq消息，通过工具类得到各个字段数据，判断tag属性值，去执行相对应的数据库操作，得到回显结果
	 * @param rabbitMessage mq消息
	 */
	@RabbitListener(queues="Login")
	public void listener(Message rabbitMessage) {
		String message = new String(rabbitMessage.getBody());
		System.err.println("manage -- Recieved: " + message);
		String tag = MessageUtil.getTag(message);
		if(tag.equals("12297")) {
			
		}else if(tag.equals("16389")) {
			
		}else if(tag.equals("12291")){
			
		}else {
			String sessionId =MessageUtil.getSessionId(message);
			String requestId = MessageUtil.getRequestId(message);
			String wsId = MessageUtil.getContentFromMsg(message, "WSID");
			switch (Integer.parseInt(tag)) {
				case Tag.LOGIN_REQUEST://登录请求
					String username = MessageUtil.getContentFromMsg(message, "username");
					String loginpassword = MessageUtil.getContentFromMsg(message, "loginpassword");
					String loginip = MessageUtil.getContentFromMsg(message, "srcip");
					User user =  userSystemService.Userlogin(username, loginpassword,loginip,sessionId);
					userloginService.SendloginstatusSer(wsId,requestId,sessionId,user);
					break;
				case 131089://登出请求
					String userId = MessageUtil.getContentFromMsg(message, Constant.USER_ID);
					if(redisutil.hHasKey(Constant.ONLINE_USERS, userId)) {
						redisutil.hdel(Constant.ONLINE_USERS, userId);
					}
					break;
				case Tag.SEND_EMAIL_REQUEST://发送邮箱验证码
					String status = MessageUtil.getContentFromMsg(message, Constant.STATUS);
					String email = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
					if("1".equals(status)) {
						if(userSystemService.UserExist(email)) {
							userloginService.SenduserexistSer(-100,sessionId);
						}else {
							String emailCode = MessageUtil.getContentFromMsg(message, Constant.EMAIL_CODE);
							emailService.mailsend(email, emailCode);
							userloginService.SenduserexistSer(1,sessionId);
						}
					}else {
						String emailCode = MessageUtil.getContentFromMsg(message, Constant.EMAIL_CODE);
						emailService.mailsend(email, emailCode);
					}
					break;
				case Tag.REGISTRY_REQUEST://注册求情
					String addEmail = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
					String addPassword = MessageUtil.getContentFromMsg(message,"Password");
					User adduser = userSystemService.Useradd(addEmail, addPassword);
					if(adduser != null) {
						int adduserId = adduser.getID();
						userloginService.RegisterSer(sessionId, adduserId);
					}
					break;
				case Tag.FORGET_PWD_REQUEST://忘记密码
					String lname = MessageUtil.getContentFromMsg(message, Constant.EMAIL);
					String lpwd = MessageUtil.getContentFromMsg(message,"Password");
					if(userSystemService.PwdModification(lname, lpwd)==1) {
						userloginService.PwdModificationSer(sessionId, 0);
					}
					break;
				//目前有接收到但未用到的Tag属性请求
				case 12291://资金冻结请求
					break;
				case 12297://成交订单资金加减 (该功能C++已实现)
					break;
				case 16389://OTC撤单请求
					
					break;
				
				default:
					break;
			}
		}
		
		
	}
}
