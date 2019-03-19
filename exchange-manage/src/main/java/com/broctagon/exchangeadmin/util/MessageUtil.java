package com.broctagon.exchangeadmin.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.broctagon.exchangeadmin.constant.Constant;
import com.broctagon.exchangeadmin.constant.Tag;
import com.broctagon.exchangeadmin.model.User;



@Component
public class MessageUtil {

	
	//id = 21
	@Value("${id}")
	private String wsId;
	
	
	/**
	 * 完成了吧数据josn化的过程，最终得到一个追加了wsId、sessionId、requestId、tag的完整josn数据
	 * @param message	数据
	 * @param sessionId	sessionId
	 * @param requestId requestId
	 * @return 完整的josn数据
	 */
	public String addTitleToMsgWsSend(String sessionId,int echoTag,User user) {
		if(user != null && sessionId != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("{ ")
				.append("\"").append(Constant.SESSION_ID).append("\" : \"").append(sessionId).append("\", ")
				.append("\"").append(Tag.TAG).append("\" : \"").append(echoTag).append("\", ")
				.append("\"").append(Constant.STATUS).append("\" : \"").append(user.getStatus()).append("\", ")
				.append("\"").append(Constant.USER_ID).append("\" : \"").append(user.getID()).append("\"")
				.append("}");
			return sb.toString();
		}else {
			throw new IllegalArgumentException("There is an empty message.");
		}
		
		
	}

	
	
	
	public static String getTag(String message) {
		if(message.contains(Tag.TAG)) {
			return getContentFromMsg(message, Tag.TAG);
		}else {
			throw new IllegalArgumentException("The request doesn't contain tag. Please check. The request is : " + message);
		}
	}

	/**
	 * 主要完成从josn数据终截取出相对应的字段属性（SessionID、requsetId、Tag）
	 * 
	 * @param message 传进来的josn数据
	 * @param mark	一般是SessionID字段或者requsetId字段或者Tag
	 * @return		返回当前josn数据的SessionId或者requsetId或者Tag
	 */
	public static String getContentFromMsg(String message, String mark) {
		String content = null;
		//如果包含SessionID
		if(message.contains(mark)) {
			//indexOf()返回第一次出现的字符的索引，查找指定字符是在字符串中的下标。在则返回所在字符串下标；不在则返回-1.  
			//substring(x)是从字符串的的第x个字符截取
			content = message.substring(message.indexOf(mark));
			int start = content.indexOf(":");
			int end = content.indexOf(",");
			//判断content中是否存在“，”
			if(end == -1) {
				//下标定到最尾部的索引下标
				end = content.length() - 1;
			}
			//trim()去掉首尾的空格
			//replaceAll("\"", "")把\全部换成“”
			content = content.substring(start + 1, end).trim().replaceAll("\"", "");
		}
		return content;
	}
	
	/**
	  *     主要完成的事是判断传进来的数据有没有josn化，没有就抛异常，有就调用getContentFromMsg方法
	 *   最终返回传进来的josn数据的SessionID。  
	 * MessageUtil工具类的获取当前消息的sessionId
	 * 
	 * @param message  	传进来的数据
	 * @return		  	 返回传进来的数据的SessionId
	 */
	public static String getSessionId(String message) {
		//如果包含SessionID
		if(message.contains(Constant.SESSION_ID)) {
			return getContentFromMsg(message, Constant.SESSION_ID);
		}else {
			throw new IllegalArgumentException("The request doesn't contain sessionId. Please check. The request is : " + message);
		}
	}
	
	
	/**
	  *     主要完成的事是判断传进来的数据有没有josn化，没有就抛异常，有就调用getContentFromMsg方法
	 *   最终返回传进来的josn数据的requestId。  
	 * MessageUtil工具类的获取当前消息的requestId
	 * 
	 * @param message  	传进来的数据
	 * @return		  	 返回传进来的数据的requestId
	 */
	public static String getRequestId(String message) {
		if(message.contains(Constant.REQUEST_ID)) {
			return getContentFromMsg(message, Constant.REQUEST_ID);
		}else {
			throw new IllegalArgumentException("The request doesn't contain requestId. Please check. The request is : " + message);
		}
	}
}
