
package com.broctagon.exchangeadmin.message;


/**
* @auther: Water
* @time: 15 Mar 2018 11:04:47
* 
*/

public class BaseMsg {

	private int Tag;
	private int WSID;
	private String SessionID;
	private String RequestID;
	
	private Object resData;
	
	public int getTag() {
		return Tag;
	}
	public void setTag(int tag) {
		Tag = tag;
	}
	public int getWSID() {
		return WSID;
	}
	public void setWSID(int wSID) {
		WSID = wSID;
	}
	public String getSessionID() {
		return SessionID;
	}
	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}
	public String getRequestID() {
		return RequestID;
	}
	public void setRequestID(String requestID) {
		RequestID = requestID;
	}
	public Object getResData() {
		return resData;
	}
	public void setResData(Object resData) {
		this.resData = resData;
	}
	
	
}
