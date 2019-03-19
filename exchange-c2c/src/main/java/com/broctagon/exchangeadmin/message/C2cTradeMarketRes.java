
package com.broctagon.exchangeadmin.message;

import com.broctagon.exchangeadmin.model.C2cTradeVolumeModel;

/**
* @auther: Water
* @time: 27 Mar 2018 15:16:05
* 
*/

public class C2cTradeMarketRes extends BaseMsg{
	
	private double currentPrice;
	private C2cTradeVolumeModel yesterday;
	private C2cTradeVolumeModel today;
	
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	public C2cTradeVolumeModel getYesterday() {
		return yesterday;
	}
	public void setYesterday(C2cTradeVolumeModel yesterday) {
		this.yesterday = yesterday;
	}
	public C2cTradeVolumeModel getToday() {
		return today;
	}
	public void setToday(C2cTradeVolumeModel today) {
		this.today = today;
	}
	
}
