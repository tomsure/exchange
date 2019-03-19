
package com.broctagon.exchangeadmin.model;

/**
* @auther: Water
* @time: 7 Mar 2018 15:11:25
* 
*/

public class C2cEntrustModel{
	
	private int id;
	private String coinName;
	private int userId;
	private double price;
	private double amount;
	private int entrustType;
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getEntrustType() {
		return entrustType;
	}
	public void setEntrustType(int entrustType) {
		this.entrustType = entrustType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
