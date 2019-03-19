
package com.broctagon.exchangeadmin.message;

import com.alibaba.fastjson.annotation.JSONField;

/**
* @auther: Water
* @time: 28 Mar 2018 11:16:41
* 
*/

public class ManagerFreezeRes{

	@JSONField(name="Tag")
	private int tag;
	
	@JSONField(name="UserID")
	private int userID;
	
	@JSONField(name="OrderID")
	private int orderID;
	
	@JSONField(name="Status")
	private int status;
	
	public int getTag() {
		return tag;
	}
	public void setTag(int tag) {
		this.tag = tag;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
