
package com.broctagon.exchangeadmin.message;

import java.util.List;

import com.broctagon.exchangeadmin.model.C2cEntrustModel;

/**
* @auther: Water
* @time: 15 Mar 2018 14:57:28
* 
*/

public class C2cEntrustListRes extends BaseMsg{
	
	private List<C2cEntrustModel> sell;
	private List<C2cEntrustModel> buy;
	
	public List<C2cEntrustModel> getSell() {
		return sell;
	}
	public void setSell(List<C2cEntrustModel> sell) {
		this.sell = sell;
	}
	public List<C2cEntrustModel> getBuy() {
		return buy;
	}
	public void setBuy(List<C2cEntrustModel> buy) {
		this.buy = buy;
	}
	
}
