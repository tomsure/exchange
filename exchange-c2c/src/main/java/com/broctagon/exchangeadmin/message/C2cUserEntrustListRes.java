
package com.broctagon.exchangeadmin.message;

import java.util.List;
import java.util.Map;

import com.broctagon.exchangeadmin.model.C2cEntrustModel;

/**
* @auther: Water
* @time: 16 Mar 2018 15:40:44
* 
*/

public class C2cUserEntrustListRes extends BaseMsg{

	private List<Map<String,Object>>  orderList;

	public List<Map<String,Object>> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Map<String,Object>> orderList) {
		this.orderList = orderList;
	}
	
}
