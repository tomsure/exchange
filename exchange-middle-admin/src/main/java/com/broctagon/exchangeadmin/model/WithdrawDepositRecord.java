package com.broctagon.exchangeadmin.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="withdrawdeposit_ecord_histroy")
public class WithdrawDepositRecord {

	@Id
	@Column(name="ID")
	private long id;
	
	@Column(name="UserID")
	private int userId;//用户id
	
	@Column(name="CoinID")
	private int coinId;//硬币id
	
	@Column(name="Amount")
	private BigDecimal amount;//数量
	
	@Column(name="SrcAddress")
	private String srcAddr;//系统地址
	
	@Column(name="DestAddr")
	private String destAddr;
	
	@Column(name="ApplyTime")
	private BigDecimal applyTime;//应用时间
	
	@Column(name="CSConfirmTime")
	private BigDecimal csConfirmTime;//确认时间
	
	@Column(name="ConfirmBlkQty")
	private int confirmBlkQty;//确认保证金
	
	@Column(name="BlKConfirmTime")
	private BigDecimal blkConfirmTime;
	
	@Column(name="Txid")
	private String txid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCoinId() {
		return coinId;
	}

	public void setCoinId(int coinId) {
		this.coinId = coinId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSrcAddr() {
		return srcAddr;
	}

	public void setSrcAddr(String srcAddr) {
		this.srcAddr = srcAddr;
	}

	public String getDestAddr() {
		return destAddr;
	}

	public void setDestAddr(String destAddr) {
		this.destAddr = destAddr;
	}

	public BigDecimal getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(BigDecimal applyTime) {
		this.applyTime = applyTime;
	}

	public BigDecimal getCsConfirmTime() {
		return csConfirmTime;
	}

	public void setCsConfirmTime(BigDecimal csConfirmTime) {
		this.csConfirmTime = csConfirmTime;
	}

	public int getConfirmBlkQty() {
		return confirmBlkQty;
	}

	public void setConfirmBlkQty(int confirmBlkQty) {
		this.confirmBlkQty = confirmBlkQty;
	}

	public BigDecimal getBlkConfirmTime() {
		return blkConfirmTime;
	}

	public void setBlkConfirmTime(BigDecimal blkConfirmTime) {
		this.blkConfirmTime = blkConfirmTime;
	}

	public String getTxid() {
		return txid;
	}

	public void setTxid(String txid) {
		this.txid = txid;
	}
	
	
}
