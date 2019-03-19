
package com.broctagon.exchangeadmin.message;

import java.util.List;

import com.broctagon.exchangeadmin.model.C2cTradeDetailModel;

/**
* @auther: Water
* @time: 22 Mar 2018 14:50:57
* 
*/

public class C2cTradeDetailRes extends BaseMsg{

	private List<C2cTradeDetailModel> orderList;

	public List<C2cTradeDetailModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<C2cTradeDetailModel> orderList) {
		this.orderList = orderList;
	}

}
