
package com.broctagon.exchangeadmin.message;

/**
* @auther: Water
* @time: 17 Mar 2018 17:20:48
* 
*/

public class C2cUpdateTradeReq extends BaseMsg{

	private int tradeId;

	public int getTradeId() {
		return tradeId;
	}

	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	
}
