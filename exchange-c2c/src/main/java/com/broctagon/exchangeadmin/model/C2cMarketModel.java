
package com.broctagon.exchangeadmin.model;

/**
* @auther: Water
* @time: 21 Mar 2018 16:00:39
* 
*/

public class C2cMarketModel {

	private int id;
	private double price;
	private double amount;
	private double total;
	private String coinName;
	private Integer entrustType;
	private Integer tradeStatus;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public Integer getEntrustType() {
		return entrustType;
	}
	public void setEntrustType(Integer entrustType) {
		this.entrustType = entrustType;
	}
	public Integer getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
