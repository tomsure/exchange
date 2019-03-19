package com.broctagon.exchangeadmin.model.UserOperation;

public class RegisterService {

	private int Tag = com.broctagon.exchangeadmin.constant.Tag.REGISTRY_RESPONSE;
	
	private int UserID;
	
	private String SessionID;
	
	private String Status;

	public RegisterService() {
		super();
	}

	public RegisterService(int userID, String sessionID, String status) {
		super();
		UserID = userID;
		SessionID = sessionID;
		Status = status;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
}
