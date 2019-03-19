package com.broctagon.exchangeadmin.service;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.broctagon.exchangeadmin.constant.Constant;
import com.broctagon.exchangeadmin.dao.LoginHistorySystem;
import com.broctagon.exchangeadmin.dao.UserSystem;
import com.broctagon.exchangeadmin.model.LoginHistory;
import com.broctagon.exchangeadmin.model.User;
import com.broctagon.exchangeadmin.util.DateUtil;
import com.broctagon.exchangeadmin.util.RedisUtil;


@Service
@Transactional
public class UserSystemService {
	
	@Autowired
	private UserSystem userSystem;
	
	@Autowired
	private LoginHistorySystem loginHistorySystem;
	
	@Autowired
	private RedisUtil redisutil;
	
	
	
	
	
	
	/**
	 * 调用数据访问层接口，获取数据判断业务
	 * @param UserName 登录名
	 * @param loginpassword 登录密码
	 * @return 回显状态
	 */
	public User Userlogin(String UserName,String loginpassword,String loginIp,String sessionId) {
		User user= userSystem.Userlogin(UserName);
		if (user != null && user.getLoginPassword().equals(loginpassword)) {
			user.setStatus(0);
			String userid = String.valueOf(user.getID());
			//添加登录历史记录信息
			LoginHistory loginHistory = new LoginHistory(user.getID(),DateUtil.getDateFormat(new Date()),loginIp);
			loginHistorySystem.save(loginHistory);
			if(redisutil.hasKey(Constant.ONLINE_USERS)) {
				if(redisutil.hHasKey(Constant.ONLINE_USERS,userid)) {
					String kickSessionId =(String) redisutil.hget(Constant.ONLINE_USERS, userid);
					user.setKickSessionId(kickSessionId);
				}
			}
			redisutil.hset(Constant.ONLINE_USERS,userid,sessionId);
		}else {
			User usererror = new User(-1);
			return usererror;
		}
		return user;
	}
	
	
	/**
	 * 根据用户名判断用户名是否存在
	 * @param username  用户名
	 * @return  true表示存在   false表示不存在
	 */
	public boolean UserExist(String username) {
		if(null != userSystem.Userlogin(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 *注册用户,添加一个用户
	 */
	public User Useradd(String addEmail,String addPassword) {
		User user = new User(addEmail,1,addPassword,DateUtil.getDateFormat(new Date()),1);
		return userSystem.save(user);
	}
	
	
	/**
	 * 修改密码
	 */
	public int PwdModification(String username,String password) {
		return userSystem.PwdModification(username, password);
	}
	
}
