package com.broctagon.exchangeadmin.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="token_order")
public class HistoryOrder {

	@Id
	@Column(name="ID")
	private BigDecimal orderId;
	
	@Column(name="UserID")
	@JoinColumn(name = "ID")
	private int userId;//用户ID
	
	@Column(name="DelegateType")
	private String delegateType;//委托类型
	
	@Column(name="Type")
	private int type;//类型
	
	@Column(name="ExcelID")
	private String excelId;
	
	@Column(name="OrderIDFrME")
	private String orderIdFromMe;
	
	@Column(name="Status")
	private String status;//状态
	
	@Column(name="Price")
	private BigDecimal price;//价格
	
	@Column(name="Amount")
	private BigDecimal qty;//数量
	
	@Column(name="TradeAmount")
	private BigDecimal amount;//贸易数量
	
	@Column(name="AveragePrice")
	private BigDecimal averagePrice;//平均价格
	
	@Column(name="OrderTime")
	private BigDecimal orderTime;//订货时间
	
	@Column(name="LastTraderTime")
	private BigDecimal tradeTime;//最后交易时间
	
	@Column(name="Symbol")
	private String cryptoPair;

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDelegateType() {
		return delegateType;
	}

	public void setDelegateType(String delegateType) {
		this.delegateType = delegateType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getExcelId() {
		return excelId;
	}

	public void setExcelId(String excelId) {
		this.excelId = excelId;
	}

	public String getOrderIdFromMe() {
		return orderIdFromMe;
	}

	public void setOrderIdFromMe(String orderIdFromMe) {
		this.orderIdFromMe = orderIdFromMe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAveragePrice() {
		return averagePrice;
	}

	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}

	public BigDecimal getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(BigDecimal orderTime) {
		this.orderTime = orderTime;
	}

	public BigDecimal getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(BigDecimal tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getCryptoPair() {
		return cryptoPair;
	}

	public void setCryptoPair(String cryptoPair) {
		this.cryptoPair = cryptoPair;
	}
	
}
