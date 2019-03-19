package com.broctagon.marketserver.util;

import org.springframework.stereotype.Component;

import com.broctagon.marketserver.constant.Tag;

@Component
public class MessageUtil {

	public static String getTag(String message) {
		if(message.contains(Tag.TAG)) {
			return getContentFromMsg(message, Tag.TAG);
		}else {
			throw new IllegalArgumentException("The request doesn't contain tag. Please check. The request is : " + message);
		}
	}

	public static String getContentFromMsg(String message, String mark) {
		String content = null;
		if(message.contains(mark)) {
			content = message.substring(message.indexOf(mark));
			int start = content.indexOf(":");
			int end = content.indexOf(",");
			if(end == -1) {
				end = content.length() - 1;
			}
			content = content.substring(start + 1, end).trim().replaceAll("\"", "");
		}
		return content;
	}
 }
