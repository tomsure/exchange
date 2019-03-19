package com.broctagon.exchangeadmin.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;



/**
 * 用户类
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name="user")
public class User {
	

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "ID")
	private int ID;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="UserAlias")
	private String UserAlias;
	
	@Column(name="UserType")
	private int UserType;
	
	@Column(name="loginPassword")
	private String loginPassword;
	
	@Column(name="TradePassword")
	private String TradePassword;
	
	@Column(name="RegisterTime")
	private String RegisterTime;
	
	@Column(name="MobilePhoneNum")
	private String MobilePhoneNum;
	
	@Column(name="Status")
	private int Status;
	
	@Column(name="IsKYC",insertable=false)
	private int IsKYC;
	
	@Column(name="IsKYCLock")
	private int IsKYCLock;
	
	@Column(name="IsHaveAddress")
	private int IsHaveAddress;
	
	@Column(name="LastLoginTime")
	private String LastLoginTime;
	
	@Column(name="Role")
	private int Role;
	
	@Transient
	@Column(insertable=false)
	private String KickSessionId;
	

	public User(int status) {
		super();
		Status = status;
	}

	public User() {
		super();
	}
	
	

	public User(String userName, int userType, String loginPassword, String registerTime, int status) {
		super();
		this.userName = userName;
		UserType = userType;
		this.loginPassword = loginPassword;
		RegisterTime = registerTime;
		Status = status;
	}

	public User(int ID, String loginPassword) {
		this.ID = ID;
		this.loginPassword = loginPassword;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAlias() {
		return UserAlias;
	}

	public void setUserAlias(String userAlias) {
		UserAlias = userAlias;
	}

	public int getUserType() {
		return UserType;
	}

	public void setUserType(int userType) {
		UserType = userType;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getTradePassword() {
		return TradePassword;
	}

	public void setTradePassword(String tradePassword) {
		TradePassword = tradePassword;
	}

	public String getRegisterTime() {
		return RegisterTime;
	}

	public void setRegisterTime(String registerTime) {
		RegisterTime = registerTime;
	}

	public String getMobilePhoneNum() {
		return MobilePhoneNum;
	}

	public void setMobilePhoneNum(String mobilePhoneNum) {
		MobilePhoneNum = mobilePhoneNum;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getIsKYC() {
		return IsKYC;
	}

	public void setIsKYC(int isKYC) {
		IsKYC = isKYC;
	}

	public int getIsKYCLock() {
		return IsKYCLock;
	}

	public void setIsKYCLock(int isKYCLock) {
		IsKYCLock = isKYCLock;
	}

	public int getIsHaveAddress() {
		return IsHaveAddress;
	}

	public void setIsHaveAddress(int isHaveAddress) {
		IsHaveAddress = isHaveAddress;
	}

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

	public int getRole() {
		return Role;
	}

	public void setRole(int role) {
		Role = role;
	}

	public String getKickSessionId() {
		return KickSessionId;
	}

	public void setKickSessionId(String kickSessionId) {
		KickSessionId = kickSessionId;
	}

	

	
	
	
	

}
