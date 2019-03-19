
package com.broctagon.exchangeadmin.message;

/**
* @auther: Water
* @time: 16 Mar 2018 16:47:26
* 
*/

public class C2cTradeReq  extends BaseMsg{
	
	private int entrustId;
	private int userId;
	private double amount;
	
	public int getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(int entrustId) {
		this.entrustId = entrustId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
