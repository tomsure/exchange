
package com.broctagon.exchangeadmin.message;

import java.util.List;

import com.broctagon.exchangeadmin.model.C2cMarketModel;

/**
* @auther: Water
* @time: 21 Mar 2018 16:02:59
* 
*/

public class C2cHistoryMarketRes extends BaseMsg{

	private List<C2cMarketModel> historyMarket;

	public List<C2cMarketModel> getHistoryMarket() {
		return historyMarket;
	}

	public void setHistoryMarket(List<C2cMarketModel> historyMarket) {
		this.historyMarket = historyMarket;
	}
	
}
