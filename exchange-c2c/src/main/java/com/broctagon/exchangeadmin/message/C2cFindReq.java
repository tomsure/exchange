
package com.broctagon.exchangeadmin.message;

/**
* @auther: Water
* @time: 22 Mar 2018 12:07:54
* 
*/

public class C2cFindReq extends BaseMsg{

	private Integer userId;
	private String coinName;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
}
