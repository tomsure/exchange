package com.broctagon.exchangeadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	JavaMailSender jms;
	
	public String mailsend(String emaile,String mailCode){
		//建立邮件消息
		SimpleMailMessage mainMessage = new SimpleMailMessage();
		//发送者
		mainMessage.setFrom("13249928868@163.com");
		//接收者
		mainMessage.setTo(emaile);
		//发送的标题
		mainMessage.setSubject("邮箱验证码！");
		//发送的内容
		mainMessage.setText("您本次操作的验证码是："+mailCode);
		jms.send(mainMessage);
		return "1";
	}

}
