
package com.broctagon.exchangeadmin.message;

import java.util.Map;

/**
* @auther: Water
* @time: 15 Mar 2018 11:15:51
* 
*/

public class C2cResultRes extends BaseMsg{
	
	private boolean result = true;
	private String reason;
	private Integer userId;
	private Map<String,Object> banklist;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Map<String,Object> getBanklist() {
		return banklist;
	}
	public void setList(Map<String,Object> banklist) {
		this.banklist = banklist;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
