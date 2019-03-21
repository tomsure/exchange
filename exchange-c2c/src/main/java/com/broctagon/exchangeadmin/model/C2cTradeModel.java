
package com.broctagon.exchangeadmin.model;

import java.util.Date;

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
	private Date createtime;
	private Date updatetime;
	private Date end1time;
	private Date end2time;
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Date getEnd1time() {
		return end1time;
	}
	public void setEnd1time(Date end1time) {
		this.end1time = end1time;
	}
	public Date getEnd2time() {
		return end2time;
	}
	public void setEnd2time(Date end2time) {
		this.end2time = end2time;
	}
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
