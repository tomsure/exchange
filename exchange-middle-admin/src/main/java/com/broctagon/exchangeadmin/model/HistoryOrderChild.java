package com.broctagon.exchangeadmin.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 历史订单
 * @author Administrator
 *
 */
@Entity
@Table(name="order_child")
public class HistoryOrderChild {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="OrderID")
	private BigDecimal orderId;//订单ID
	
	@Column(name="PeerOrderID")
	private BigDecimal tradeOrderId;
	
	@Column(name="Price")
	private BigDecimal price;//价格
	
	@Column(name="TradeAmount")
	private BigDecimal amount;//贸易数量
	
	@Column(name="tradeTime")
	private BigDecimal tradeTime;//贸易时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getOrderId() {
		return orderId;
	}

	public void setOrderId(BigDecimal orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getTradeOrderId() {
		return tradeOrderId;
	}

	public void setTradeOrderId(BigDecimal tradeOrderId) {
		this.tradeOrderId = tradeOrderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(BigDecimal tradeTime) {
		this.tradeTime = tradeTime;
	}
	
}
