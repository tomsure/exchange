package com.broctagon.exchangeadmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ó²±Ò(ÐéÄâ±Ò)
 * @author Administrator
 *
 */

@Entity
@Table(name="coin")
public class Crypto {

	@Id
	@Column(name="ID")
	private int id;
	
	@Column(name="CoinName")
	private String coinName;//±ÒÃû³Æ
	
	@Column(name="BlockChainID")
	private int chainId;//¿éÁ´id
	
	@Column(name="WithdrawFee")
	private double withdrawalFee;//Ìá¿î·Ñ
	
	@Column(name="`Describe`")
	private String descrip;//ÃèÊö
	
	@Column(name="IsBaseCoin")
	private int baseCoin;//»ù´¡Ó²±Ò
	
	@Column(name="Status")
	private int status;//×´Ì¬

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public int getChainId() {
		return chainId;
	}

	public void setChainId(int chainId) {
		this.chainId = chainId;
	}

	public double getWithdrawalFee() {
		return withdrawalFee;
	}

	public void setWithdrawalFee(double withdrawalFee) {
		this.withdrawalFee = withdrawalFee;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public int getBaseCoin() {
		return baseCoin;
	}

	public void setBaseCoin(int baseCoin) {
		this.baseCoin = baseCoin;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
