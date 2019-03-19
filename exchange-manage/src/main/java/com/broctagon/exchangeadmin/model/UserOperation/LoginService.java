package com.broctagon.exchangeadmin.model.UserOperation;

public class LoginService {
	
	private String WSID;
	
	private String RequestID;
	
	private String SessionID;
	
	private int Status;
	
	private int UserID;
	
	private String kickSessionId;
	
	private int Tag = com.broctagon.exchangeadmin.constant.Tag.LOGIN_RESPONSE;

	public LoginService(String wSID, String requestID, String sessionID, int status, int userID,String kickSessionId) {
		super();
		WSID = wSID;
		RequestID = requestID;
		SessionID = sessionID;
		Status = status;
		UserID = userID;
		this.kickSessionId = kickSessionId;
	}

	public String getWSID() {
		return WSID;
	}

	public void setWSID(String wSID) {
		WSID = wSID;
	}

	public String getRequestID() {
		return RequestID;
	}

	public void setRequestID(String requestID) {
		RequestID = requestID;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getKickSessionId() {
		return kickSessionId;
	}

	public void setKickSessionId(String kickSessionId) {
		this.kickSessionId = kickSessionId;
	}

	

	
	

}
