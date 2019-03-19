
package com.broctagon.exchangeadmin.model;

/**
* @auther: Water
* @time: 21 Mar 2018 10:59:40
* 
*/

public class C2cTradeDetailModel extends C2cEntrustModel{
	
	private int userId;
	private double price;
	private int entrustType;
	private double amount;	
	
	private String coinName;
	private String entrustId;
	private String oppositUserName;
	private String bankName;
	private String bankAccount;
	
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
	public String getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(String entrustId) {
		this.entrustId = entrustId;
	}
	public String getOppositUserName() {
		return oppositUserName;
	}
	public void setOppositUserName(String oppositUserName) {
		this.oppositUserName = oppositUserName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
}
