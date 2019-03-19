
package com.broctagon.exchangeadmin.model;

/**
* @auther: Water
* @time: 14 Mar 2018 11:41:26
* 
*/

public class C2cTradeModel {

	private int id;
	private int entrustId;
	private int tradeUserId;
	private double amount;
	private int tradeStatus;
	private int coinName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEntrustId() {
		return entrustId;
	}
	public void setEntrustId(int entrustId) {
		this.entrustId = entrustId;
	}
	public int getTradeUserId() {
		return tradeUserId;
	}
	public void setTradeUserId(int tradeUserId) {
		this.tradeUserId = tradeUserId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(int tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public int getCoinName() {
		return coinName;
	}
	public void setCoinName(int coinName) {
		this.coinName = coinName;
	}
	
}
