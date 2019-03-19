package com.broctagon.exchangeadmin.model.UserOperation;

public class StatusAll {
	
	private int Status;
	
	private int Tag = com.broctagon.exchangeadmin.constant.Tag.SEND_EMAIL_RESPONSE;
	
	private String SessionID;

	

	public StatusAll(int status, String sessionID) {
		super();
		Status = status;
		SessionID = sessionID;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
	
	
	
}
