package com.broctagon.exchangeadmin.util;

import com.broctagon.exchangeadmin.constant.Tag;

public class TagJudgeUtil {
	
	
	
	public static int Tagjudge(String messagetag) {
		int  tag = -1;
		switch (Integer.parseInt(messagetag)) {
			case Tag.LOGIN_REQUEST:
				tag = Tag.LOGIN_RESPONSE;
				break;
			case Tag.LOGOUT_REQUEST:
				tag = Tag.LOGOUT_RESPONSE;
				break;
			default:
				break;
		}
		return tag;
	}
	
}
