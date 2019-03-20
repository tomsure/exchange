
package com.broctagon.exchangeadmin.message;

import java.util.List;
import java.util.Map;

import com.broctagon.exchangeadmin.model.C2cTradeDetailModel;

/**
* @auther: Water
* @time: 22 Mar 2018 14:50:57
* 
*/

public class C2cTradeDetailRes extends BaseMsg{

	private List<Map<String,Object>> orderList;

	public List<Map<String,Object>> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Map<String,Object>> orderList) {
		this.orderList = orderList;
	}

}
