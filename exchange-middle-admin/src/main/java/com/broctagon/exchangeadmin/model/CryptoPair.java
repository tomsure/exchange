package com.broctagon.exchangeadmin.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="symbol")
public class CryptoPair {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="BaseCoinID")
	private int baseCoinId;//硬币id
	
	@Column(name="TradeCoinID")
	private int tradeCoinId;//交易id
	
	@Column(name="BuyFee")
	private BigDecimal buyFee;//（买）金额
	
	@Column(name="SellFee")
	private BigDecimal sellFee;//（卖）金额
	
	@Column(name="TradeBeginTime")
	private String tradeBeginTime;//交易开始时间
	
	@Column(name="TradeEndTime")
	private String tradeEndTime;//交易时间
	
	@Column(name="MinBuyQty")
	private BigDecimal minBuyQty;//买数量
	
	@Column(name="MinSellQty")
	private BigDecimal minSellQty;//卖数量
	
	@Column(name="MaxDepositQty")
	private BigDecimal maxDepositQty;//最大存放数量？

	@Column(name = "Status")
	private int status;//状态
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaseCoinId() {
		return baseCoinId;
	}

	public void setBaseCoinId(int baseCoinId) {
		this.baseCoinId = baseCoinId;
	}

	public int getTradeCoinId() {
		return tradeCoinId;
	}

	public void setTradeCoinId(int tradeCoinId) {
		this.tradeCoinId = tradeCoinId;
	}

	public BigDecimal getBuyFee() {
		return buyFee;
	}

	public void setBuyFee(BigDecimal buyFee) {
		this.buyFee = buyFee;
	}

	public BigDecimal getSellFee() {
		return sellFee;
	}

	public void setSellFee(BigDecimal sellFee) {
		this.sellFee = sellFee;
	}

	public String getTradeBeginTime() {
		return tradeBeginTime;
	}

	public void setTradeBeginTime(String tradeBeginTime) {
		this.tradeBeginTime = tradeBeginTime;
	}

	public String getTradeEndTime() {
		return tradeEndTime;
	}

	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}

	public BigDecimal getMinBuyQty() {
		return minBuyQty;
	}

	public void setMinBuyQty(BigDecimal minBuyQty) {
		this.minBuyQty = minBuyQty;
	}

	public BigDecimal getMinSellQty() {
		return minSellQty;
	}

	public void setMinSellQty(BigDecimal minSellQty) {
		this.minSellQty = minSellQty;
	}

	public BigDecimal getMaxDepositQty() {
		return maxDepositQty;
	}

	public void setMaxDepositQty(BigDecimal maxDepositQty) {
		this.maxDepositQty = maxDepositQty;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
