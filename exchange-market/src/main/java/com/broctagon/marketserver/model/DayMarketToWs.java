package com.broctagon.marketserver.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.broctagon.marketserver.constant.Tag;

public class DayMarketToWs {

	@JSONField(name="Tag")
	private int tag = Tag.MARKET_INFO;
	
	@JSONField(name="Symbol")
	private String symbol;
	
	@JSONField(name="SessionID")
	private String sessionID;
	
	@JSONField(name="Yesterday")
	private DayMarket yesterday;
	
	@JSONField(name="Today")
	private DayMarket today;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public DayMarket getYesterday() {
		return yesterday;
	}

	public void setYesterday(DayMarket yesterday) {
		this.yesterday = yesterday;
	}

	public DayMarket getToday() {
		return today;
	}

	public void setToday(DayMarket today) {
		this.today = today;
	}
	
}
