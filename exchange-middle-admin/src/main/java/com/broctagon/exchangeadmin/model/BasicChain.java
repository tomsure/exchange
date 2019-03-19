package com.broctagon.exchangeadmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 区块信息表
 * @author Administrator
 *
 */




@Entity
@Table(name="blockchain_info")
public class BasicChain {

	@Id
	@Column(name="ID")
	private int chainId;//链id
	
	@Column(name="ChainName")
	private String chainName;//链名

	public BasicChain(int chainId, String chainName) {
		super();
		this.chainId = chainId;
		this.chainName = chainName;
	}

	public int getChainId() {
		return chainId;
	}

	public void setChainId(int chainId) {
		this.chainId = chainId;
	}

	public String getChainName() {
		return chainName;
	}

	public void setChainName(String chainName) {
		this.chainName = chainName;
	}
	
}
