
package com.broctagon.exchangeadmin.message;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
* @auther: Water
* @time: 28 Mar 2018 11:20:08
* 
*/

public class ManagerTradeReq extends BaseMsg{

	@JSONField(name="Tag")
	private int tag;
	
	@JSONField(name="Symbol")
	private String symbol;
	
	@JSONField(name="TransType")
	private int transType;
	
	@JSONField(name="AskUserID")
	private int askUserID;
	
	@JSONField(name="AskOrderID")
	private int askOrderID;
	
	@JSONField(name="BidUserID")
	private int bidUserID;
	
	@JSONField(name="BidOrderID")
	private int bidOrderID;
	
	@JSONField(name="DealNum")
	private double DealNum;
	
	@JSONField(name="DealPrice")
	private double DealPrice;
	
	@JSONField(name="TransactionTime")
	private Timestamp transactionTime;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getTransType() {
		return transType;
	}
	public void setTransType(int transType) {
		this.transType = transType;
	}
	public int getAskUserID() {
		return askUserID;
	}
	public void setAskUserID(int askUserID) {
		this.askUserID = askUserID;
	}
	public int getAskOrderID() {
		return askOrderID;
	}
	public void setAskOrderID(int askOrderID) {
		this.askOrderID = askOrderID;
	}
	public int getBidUserID() {
		return bidUserID;
	}
	public void setBidUserID(int bidUserID) {
		this.bidUserID = bidUserID;
	}
	public int getBidOrderID() {
		return bidOrderID;
	}
	public void setBidOrderID(int bidOrderID) {
		this.bidOrderID = bidOrderID;
	}
	public double getDealNum() {
		return DealNum;
	}
	public void setDealNum(double dealNum) {
		DealNum = dealNum;
	}
	public double getDealPrice() {
		return DealPrice;
	}
	public void setDealPrice(double dealPrice) {
		DealPrice = dealPrice;
	}
	public Timestamp getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

}
