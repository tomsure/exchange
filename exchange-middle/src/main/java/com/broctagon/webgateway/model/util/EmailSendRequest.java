package com.broctagon.webgateway.model.util;


public class EmailSendRequest {
	
	private int Tag = com.broctagon.webgateway.constant.Tag.SEND_EMAIL_REQUEST;
	
	private String Email;

	private String EmailCode;
	
	private String RequestID;
	
	private String SessionID;
	
	private int WSID;
	
	private String Status;

	public int getTag() {
		return Tag;
	}

	public void setTag(int Tag) {
		this.Tag = Tag;
	}

	public String getDest() {
		return Email;
	}

	public void setDest(String Email) {
		this.Email = Email;
	}

	public String getCode() {
		return EmailCode;
	}

	public void setCode(String EmailCode) {
		this.EmailCode = EmailCode;
	}

	public String getRequestID() {
		return RequestID;
	}

	public void setRequestID(String RequestID) {
		this.RequestID = RequestID;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String SessionID) {
		this.SessionID = SessionID;
	}

	public int getwSID() {
		return WSID;
	}

	public void setwSID(int WSID) {
		this.WSID = WSID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}
	
	
	
}
