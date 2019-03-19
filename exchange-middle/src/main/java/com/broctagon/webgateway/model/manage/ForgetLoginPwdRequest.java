package com.broctagon.webgateway.model.manage;

public class ForgetLoginPwdRequest {

	private int Tag = com.broctagon.webgateway.constant.Tag.FORGET_PWD_REQUEST;
	
	private String Email;
	
	private String Password;
	
	private String RequestID;

	public int getTag() {
		return Tag;
	}

	public void setTag(int tag) {
		Tag = tag;
	}

	public String getEMail() {
		return Email;
	}

	public void setEMail(String eMail) {
		Email = eMail;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getRequestID() {
		return RequestID;
	}

	public void setRequestID(String requestID) {
		RequestID = requestID;
	}
	
}
