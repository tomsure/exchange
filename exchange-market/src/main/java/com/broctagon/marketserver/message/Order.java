package com.broctagon.marketserver.message;

public class Order {

	private int Tag;
	
	private String Symbol;
	
	private int TransType;
	
	private String AskOrderID;
	
	private String BidOrderID;
	
	private double DealNum;
	
	private double DealPrice;
	
	private String TransactTime;

	
	public int getTag() {
		return Tag;
	}

	public void setTag(int tag) {
		Tag = tag;
	}

	public String getSymbol() {
		return Symbol;
	}

	public void setSymbol(String symbol) {
		Symbol = symbol;
	}

	public int getTransType() {
		return TransType;
	}

	public void setTransType(int transType) {
		TransType = transType;
	}

	public String getAskOrderID() {
		return AskOrderID;
	}

	public void setAskOrderID(String askOrderID) {
		AskOrderID = askOrderID;
	}

	public String getBidOrderID() {
		return BidOrderID;
	}

	public void setBidOrderID(String bidOrderID) {
		BidOrderID = bidOrderID;
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

	public String getTransactTime() {
		return TransactTime;
	}

	public void setTransactTime(String transactTime) {
		TransactTime = transactTime;
	}

	@Override
	public String toString() {
		return "Order [Tag=" + Tag + ", Symbol=" + Symbol + ", TransType=" + TransType + ", AskOrderID=" + AskOrderID
				+ ", BidOrderID=" + BidOrderID + ", DealNum=" + DealNum + ", DealPrice=" + DealPrice + ", TransactTime="
				+ TransactTime + "]";
	}
	
	
	
}
