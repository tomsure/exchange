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
	private int userId;//�û�ID
	
	@Column(name="DelegateType")
	private String delegateType;//ί������
	
	@Column(name="Type")
	private int type;//����
	
	@Column(name="ExcelID")
	private String excelId;
	
	@Column(name="OrderIDFrME")
	private String orderIdFromMe;
	
	@Column(name="Status")
	private String status;//״̬
	
	@Column(name="Price")
	private BigDecimal price;//�۸�
	
	@Column(name="Amount")
	private BigDecimal qty;//����
	
	@Column(name="TradeAmount")
	private BigDecimal amount;//ó������
	
	@Column(name="AveragePrice")
	private BigDecimal averagePrice;//ƽ���۸�
	
	@Column(name="OrderTime")
	private BigDecimal orderTime;//����ʱ��
	
	@Column(name="LastTraderTime")
	private BigDecimal tradeTime;//�����ʱ��
	
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
