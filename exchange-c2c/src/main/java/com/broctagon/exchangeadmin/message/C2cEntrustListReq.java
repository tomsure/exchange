
package com.broctagon.exchangeadmin.message;

/**
* @auther: Water
* @time: 15 Mar 2018 11:22:08
* 
*/

public class C2cEntrustListReq extends BaseMsg{
	
	private int coinId;
	private String coinName;
	private int userId;
	
	public int getCoinId() {
		return coinId;
	}
	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
