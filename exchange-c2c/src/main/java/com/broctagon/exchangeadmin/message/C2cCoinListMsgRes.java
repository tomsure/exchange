
package com.broctagon.exchangeadmin.message;

import java.util.List;

import com.broctagon.exchangeadmin.model.C2cCoinModel;

/**
* @auther: Water
* @time: 16 Mar 2018 14:53:20
* 
*/

public class C2cCoinListMsgRes extends BaseMsg{

	private List<C2cCoinModel> coinList;

	public List<C2cCoinModel> getCoinList() {
		return coinList;
	}

	public void setCoinList(List<C2cCoinModel> coinList) {
		this.coinList = coinList;
	}

}
