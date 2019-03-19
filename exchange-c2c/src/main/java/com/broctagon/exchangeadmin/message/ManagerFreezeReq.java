
package com.broctagon.exchangeadmin.message;

import com.alibaba.fastjson.annotation.JSONField;


/**
* @auther: Water
* @time: 27 Mar 2018 18:34:53
* 
*/

public class ManagerFreezeReq extends BaseMsg{

	@JSONField(name="Tag")
	private int tag;
	
	@JSONField(name="UserID")
	private Integer userID;
	
	@JSONField(name="OrderID")
	private Integer orderID;
	
	@JSONField(name="Symbol")
	private String symbol;
	
	@JSONField(name="TransType")
	private Integer transType;
	
	@JSONField(name="OrderNumber")
	private Double orderNumber;
	
	@JSONField(name="Price")
	private Double price;
	
	@JSONField(name="SrvIdentify")
	private String srvIdentify;
	 
	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public Integer getUserID() {
		return userID;
	}
	
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	 
	public Integer getOrderID() {
		return orderID;
	}
	
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	public String getSymbol() {
		return symbol;
	}
	 
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	  
	public Integer getTransType() {
		return transType;
	}
	
	public void setTransType(Integer transType) {
		this.transType = transType;
	}
	
	public Double getOrderNumber() {
		return orderNumber;
	}
	
	public void setOrderNumber(Double orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getSrvIdentify() {
		return srvIdentify;
	}
	
	public void setSrvIdentify(String srvIdentify) {
		this.srvIdentify = srvIdentify;
	}
	
}
