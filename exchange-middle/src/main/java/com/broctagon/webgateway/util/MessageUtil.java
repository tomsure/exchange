package com.broctagon.webgateway.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.broctagon.webgateway.constant.Constant;
import com.broctagon.webgateway.constant.Tag;

@Component
public class MessageUtil {

	// wsId = 21
	@Value("${id}")
	private String wsId;
	
	/**
	 * 完成了吧数据josn化的过程，最终得到一个追加了wsId、sessionId的完整josn数据
	 * 相比addTitleToMsg方法追加少了一个requsetId。
	 * @param message	数据
	 * @param sessionId	sessionId
	 * @return 缺少requsetID的josn数据
	 */
	public String addTitleToMsg(String message, String sessionId) {
		if(StringUtils.isEmpty(message)) {
			return null;
		}else if(message.indexOf("[") == 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("{ ")
			  .append("\"").append(Constant.WS_ID).append("\" : ").append(wsId).append(", ")
			  .append("\"").append(Constant.SESSION_ID).append("\" : \"").append(sessionId).append("\", ")
			  .append("\"").append(Constant.CONTENT).append("\" : ").append(message)
			  .append("}");
			return sb.toString();
		}else if(message.contains("{")) {
			return message.replace("{", "{ \"" + Constant.WS_ID + "\" : " + wsId + ", \""+ Constant.SESSION_ID + "\" : \"" + sessionId + "\", ");
		}else {
			throw new IllegalArgumentException("Message is not json format. The message is : " + message);
		}
		 
	}

	
	/**
	 * 完成了吧数据josn化的过程，最终得到一个追加了wsId、sessionId、requestId的完整josn数据
	 * 相比addTitleToMsg方法追加多了requsetId。
	 * @param message	数据
	 * @param sessionId	sessionId
	 * @param requestId requestId
	 * @return 完整的josn数据
	 */
	public String addTitleToMsgWsSend(String message, String sessionId, String requestId) {
		if(StringUtils.isEmpty(message)) {
			return null;
		}else if(message.indexOf("[") == 0) {
			StringBuilder sb = new StringBuilder();
			sb.append("{ ")
			  .append("\"").append(Constant.WS_ID).append("\" : ").append(wsId).append(", ")
			  .append("\"").append(Constant.SESSION_ID).append("\" : \"").append(sessionId).append("\", ")
			  .append("\"").append(Constant.REQUEST_ID).append("\" : \"").append(requestId).append("\", ")
			  .append("\"").append(Constant.CONTENT).append("\" : ").append(message)
			  .append("}");
			return sb.toString();
		}else if(message.contains("{")) {
			return message.replace("{", "{ \"" + Constant.WS_ID + "\" : " + wsId + ", \""+ Constant.SESSION_ID + "\" : \"" + sessionId + "\", \"" + Constant.REQUEST_ID + "\" : \"" + requestId + "\", ");
		}else {
			throw new IllegalArgumentException("Message is not json format. The message is : " + message);
		}
		 
	}
	
	/**
	  *     主要完成的事是判断传进来的数据有没有包含Tag，没有就抛异常，有就调用getContentFromMsg方法
	 *   最终返回传进来的josn数据的Tag。  
	 * MessageUtil工具类的获取当前消息的Tag
	 * 
	 * @param message  	传进来的数据
	 * @return		  	 返回传进来的数据的Tag
	 */
	public static String getTag(String message) {
		if(message.contains(Tag.TAG)) {
			return getContentFromMsg(message, Tag.TAG);
		}else {
			throw new IllegalArgumentException("The request doesn't contain tag. Please check. The request is : " + message);
		}
	}

	
	/**
	 * 主要完成从josn数据终截取出相对应的字段属性（SessionID、requsetId、Tag.....）
	 * 
	 * @param message 传进来的josn数据
	 * @param mark	一般是SessionID字段或者requsetId字段或者Tag等
	 * @return		返回当前josn数据的SessionId或者requsetId或者Tag等
	 */
	public static String getContentFromMsg(String message, String mark) {
		String content = null;
		if(message.contains(mark)) {
			content = message.substring(message.indexOf(mark));
			int start = content.indexOf(":");
			int end = content.indexOf(",");
			int toEnd = content.indexOf("}");
			if(end == -1 && toEnd == -1) {
				end = content.length() - 1;
			}
			if(toEnd != -1 && end != -1) {
				if(toEnd < end) {
					end = toEnd;
				}
			}
			if(toEnd != -1 && end == -1) {
				end = toEnd;
			}
			content = content.substring(start + 1, end).trim().replaceAll("\"", "");
		}
		return content;
	}
	
	/**
	  *     主要完成的事是判断传进来的数据有没有包含SessionID，没有就抛异常，有就调用getContentFromMsg方法
	 *   最终返回传进来的josn数据的SessionID。  
	 * MessageUtil工具类的获取当前消息的sessionId
	 * 
	 * @param message  	传进来的数据
	 * @return		  	 返回传进来的数据的SessionId
	 */
	public static String getSessionId(String message) {
		if(message.contains(Constant.SESSION_ID)) {
			return getContentFromMsg(message, Constant.SESSION_ID);
		}else {
			throw new IllegalArgumentException("The request doesn't contain sessionId. Please check. The request is : " + message);
		}
	}
	
	/**
	  *     主要完成的事是判断传进来的数据有没有包含requestId，没有就抛异常，有就调用getContentFromMsg方法
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
