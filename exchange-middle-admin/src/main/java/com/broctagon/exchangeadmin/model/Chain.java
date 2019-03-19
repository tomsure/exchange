package com.broctagon.exchangeadmin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blockchain_info")
public class Chain {
	
	@Id
	@Column(name="ID")
	private int id;

	@Column(name="ChainType")
	private int chainId;//链类型ID
	
	@Column(name="ChainName")
	private String chainName;//链名
	
	@Column(name="ChainUrl")
	private String url;//链式URL
	
	@Column(name="ChainUserName")
	private String userName;//链表用户名
	
	@Column(name="ChainPWD")
	private String password;//链PWD
	
	@Column(name="ScanStartBlock")
	private int startScanBlockId;//扫描初始块
	
	@Column(name="ConfirmBlkQty")
	private int confirmNo;//确认保证金
	
	@Column(name="ChainLabel")
	private String label;//链式标签
	
	@Column(name="`Describe`")
	private String descrip;//描述
	
	@Column(name="Status")
	private int status;//状态

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStartScanBlockId() {
		return startScanBlockId;
	}

	public void setStartScanBlockId(int startScanBlockId) {
		this.startScanBlockId = startScanBlockId;
	}

	public int getConfirmNo() {
		return confirmNo;
	}

	public void setConfirmNo(int confirmNo) {
		this.confirmNo = confirmNo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
