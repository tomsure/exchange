package com.broctagon.exchangeadmin.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login_history")
public class LoginHistory {
	
	@Id
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "UserID")
	private int UserID;
	
	@Column(name = "LoginTime")
	private String LoginTime;
	
	@Column(name = "LoginIp")
	private String LoginIp;
	
	private String Region;
	
	private String Action;
	
	
	

	public LoginHistory(int userID, String loginTime, String loginIp) {
		super();
		UserID = userID;
		LoginTime = loginTime;
		LoginIp = loginIp;
	}

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

	public String getLoginTime() {
		return LoginTime;
	}

	public void setLoginTime(String loginTime) {
		LoginTime = loginTime;
	}

	public String getLoginIp() {
		return LoginIp;
	}

	public void setLoginIp(String loginIp) {
		LoginIp = loginIp;
	}

	public String getRegion() {
		return Region;
	}

	public void setRegion(String region) {
		Region = region;
	}

	public String getAction() {
		return Action;
	}

	public void setAction(String action) {
		Action = action;
	}
	
	
	
}
