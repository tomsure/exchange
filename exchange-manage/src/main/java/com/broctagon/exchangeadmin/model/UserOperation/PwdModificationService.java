package com.broctagon.exchangeadmin.model.UserOperation;

public class PwdModificationService {
	
	private int Tag = com.broctagon.exchangeadmin.constant.Tag.FORGET_PWD_RESPONSE;
	
	private String SessionID;
	
	private int Status;

	public PwdModificationService(String sessionId, int status) {
		super();
		SessionID = sessionId;
		Status = status;
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
	
	
}
