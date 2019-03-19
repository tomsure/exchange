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
	private int chainId;//������ID
	
	@Column(name="ChainName")
	private String chainName;//����
	
	@Column(name="ChainUrl")
	private String url;//��ʽURL
	
	@Column(name="ChainUserName")
	private String userName;//�����û���
	
	@Column(name="ChainPWD")
	private String password;//��PWD
	
	@Column(name="ScanStartBlock")
	private int startScanBlockId;//ɨ���ʼ��
	
	@Column(name="ConfirmBlkQty")
	private int confirmNo;//ȷ�ϱ�֤��
	
	@Column(name="ChainLabel")
	private String label;//��ʽ��ǩ
	
	@Column(name="`Describe`")
	private String descrip;//����
	
	@Column(name="Status")
	private int status;//״̬

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
