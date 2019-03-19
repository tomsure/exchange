
package com.broctagon.exchangeadmin.message;

/**
* @auther: Water
* @time: 15 Mar 2018 11:04:15
* 
*/

public class C2cEntrustReq extends BaseMsg{

	private int userId;
	private double price;
	private int entrustType;
	private double amount;
	private String coinName;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getEntrustType() {
		return entrustType;
	}
	public void setEntrustType(int entrustType) {
		this.entrustType = entrustType;
	}	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
}
