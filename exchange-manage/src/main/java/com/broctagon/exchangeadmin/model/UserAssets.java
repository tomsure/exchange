package com.broctagon.exchangeadmin.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_assets")
public class UserAssets {
	
	@Id
	@Column(name = "ID")
	private int ID;
	
	@Column(name="UserID")
	private int UserID;
	
	@Column(name="CoinID")
	private int CoinID;
	
	@Column(name="Total")
	private BigDecimal Total;
	
	@Column(name="Available")
	private BigDecimal Available;
	
	@Column(name="Frozen")
	private BigDecimal Frozen;
	
	@Column(name="Valuation")
	private BigDecimal Valuation;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getCoinID() {
		return CoinID;
	}

	public void setCoinID(int coinID) {
		CoinID = coinID;
	}

	public BigDecimal getTotal() {
		return Total;
	}

	public void setTotal(BigDecimal total) {
		Total = total;
	}

	public BigDecimal getAvailable() {
		return Available;
	}

	public void setAvailable(BigDecimal available) {
		Available = available;
	}

	public BigDecimal getFrozen() {
		return Frozen;
	}

	public void setFrozen(BigDecimal frozen) {
		Frozen = frozen;
	}

	public BigDecimal getValuation() {
		return Valuation;
	}

	public void setValuation(BigDecimal valuation) {
		Valuation = valuation;
	}

	
	
	

}
