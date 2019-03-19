
package com.broctagon.exchangeadmin.message;

import java.util.List;

import com.broctagon.exchangeadmin.model.C2cEntrustModel;

/**
* @auther: Water
* @time: 16 Mar 2018 15:40:44
* 
*/

public class C2cUserEntrustListRes extends BaseMsg{

	private List<C2cEntrustModel>  orderList;

	public List<C2cEntrustModel> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<C2cEntrustModel> orderList) {
		this.orderList = orderList;
	}
	
}
