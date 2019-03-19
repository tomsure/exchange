package com.broctagon.marketserver.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.broctagon.marketserver.constant.Tag;

public class CandleToWs {

	@JSONField(name="Tag")
	private int tag = Tag.HISOTRICAL_BARS;
	
	@JSONField(name="SessionID")
	private String sessionID;
	
	@JSONField(name="Timeframe")
	private String timeframe;
	
	@JSONField(name="MarketHistory")
	private List<Candle> marketHistory;

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public List<Candle> getMarketHistory() {
		return marketHistory;
	}

	public void setMarketHistory(List<Candle> marketHistory) {
		this.marketHistory = marketHistory;
	}
	
}
