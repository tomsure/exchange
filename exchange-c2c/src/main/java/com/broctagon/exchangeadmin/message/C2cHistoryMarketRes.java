
package com.broctagon.exchangeadmin.message;

import java.util.List;
import java.util.Map;

import com.broctagon.exchangeadmin.model.C2cMarketModel;

/**
* @auther: Water
* @time: 21 Mar 2018 16:02:59
* 
*/

public class C2cHistoryMarketRes extends BaseMsg{

	private List<Map<String,Object>> historyMarket;

	public List<Map<String,Object>> getHistoryMarket() {
		return historyMarket;
	}

	public void setHistoryMarket(List<Map<String,Object>> historyMarket) {
		this.historyMarket = historyMarket;
	}
	
}
