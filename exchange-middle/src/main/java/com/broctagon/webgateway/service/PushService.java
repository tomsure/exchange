package com.broctagon.webgateway.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.broctagon.webgateway.constant.Constant;

public class PushService {

	/**
	 * 主要完成了sessionId的存储到集合里（ConcurrentHashMap<String,Set<String>>集合）
	 * 如果传进来的Token没有在ConcurrentHashMap集合中存在key就会新建Set<String>保存sessionId
	 * 最后再把Token当key,Set当值存进ConcurrentHashMap集合中
	 * @param token 用来当做key的值(前端没传默认是--"RKT/BTC")
	 * @param sessionId  当前消息的sessionId
	 */
	public static void addPushSessionId(String token, String sessionId) {
		//判断needToPushUsers集合里是否有包含token这个key值
		if(Constant.needToPushUsers.containsKey(token)) {
			//如果有就先获取对应得set集合，再把sessionId值添加进去
			Constant.needToPushUsers.get(token).add(sessionId);
		}else {
			//如果没有就创建一个HashSet集合，然后把sessionId添加进去
			Set<String> sessionIds = Collections.synchronizedSet(new HashSet<String>());
			sessionIds.add(sessionId);
			//最后把HashSet添加到needToPushUsers这个大集合里面
			Constant.needToPushUsers.put(token, sessionIds);
		}
	}
	
	/**
	 * 连接断开后执行，清除Map中存储的sessionid
	 * @param sessionId
	 */
	public static void removePushSessionId(String sessionId) {
		Constant.needToPushUsers.entrySet().parallelStream().forEach(entry ->{
    		if(entry.getValue().contains(sessionId)) {
    			entry.getValue().remove(sessionId);
    		}
    	});
		
	}
	
	
	
	
	public static void addSessionId(String userId, String sessionId) {
		Constant.usersSessionId.put(userId, sessionId);
		System.err.println("添加userId为"+userId+"的sessionId为"+sessionId);
	}
	
	
	
	public static void removeSessionId(String sessionId) {
		for (Map.Entry<String, String> entry : Constant.usersSessionId.entrySet()) {
			if(entry.getValue().equals(sessionId)){
				Constant.usersSessionId.remove(entry.getKey());
				System.err.println("删除userId为"+entry.getKey()+"的sessionId为"+sessionId);
				break;
			}
		}
		
	}
	
	
	public static String selectSessionId(String userId) {
			
		return Constant.usersSessionId.get(userId);
		
	}
	
}
