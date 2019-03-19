
package com.broctagon.exchangeadmin.model;

import java.sql.Timestamp;

/**
* @auther: Water
* @time: 28 Mar 2018 11:33:33
* 
*/

public class C2cTradeAccountModel {

	private int tradeid;
	private int traderId;
	private int entrusterId;
	private String coinName;
	private int entrustType;
	private double amount; 
	private double price;
	private Timestamp updatetime;
	
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public int getTraderId() {
		return traderId;
	}
	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}
	public int getEntrusterId() {
		return entrusterId;
	}
	public void setEntrusterId(int entrusterId) {
		this.entrusterId = entrusterId;
	}
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Timestamp getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Timestamp updatetime) {
		this.updatetime = updatetime;
	}
	
}
