package com.broctagon.webgateway.constant;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

import com.google.gson.Gson;

public class Constant {
	
	public static final String WS_ID = "WSID";
	
	public static final String SESSION_ID = "SessionID";
	
	public static final String TOKEN = "Token";
	
	public static final String USER_ID = "UserID";
	
	public static final String CONTENT = "Content";
	
	public static final String REQUEST_ID = "RequestID";
	
	public static final String VERIFY_CODE = "vcode";
	
	public static final String EMAIL = "Email";
	
	public static final String EMAIL_CODE = "EmailCode";
	
	public static final String STATUS = "Status";
	
	public static final String TRADINGTIME = "TradingTime";
	//早上开始交易时间
	public static final String MORNINGSTART = "MorningStart";
	//早上结束交易时间
	public static final String MORNINGEND = "MorningEnd";
	//下午开始交易时间
	public static final String AFTERNOONSTART = "AfternoonStart";
	//下午结束交易时间
	public static final String AFTERNOONEND = "AfternoonEnd";
	
	public static Map<String, Set<String>> needToPushUsers = new ConcurrentHashMap<>();
	
	public static Map<String, String> usersSessionId = new ConcurrentHashMap<>();
	
	public static Map<String, String> emailMaps = new ConcurrentHashMap<>();

	public static final Gson GSON = new Gson();
}
