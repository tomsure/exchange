
package com.broctagon.exchangeadmin.service;

import com.broctagon.exchangeadmin.message.BaseMsg;
import com.broctagon.exchangeadmin.message.C2cResultRes;

/**
* @auther: Water
* @time: 16 Mar 2018 16:44:00
* 
*/

public interface C2cTradeService {

	public BaseMsg trade(String req);
	public C2cResultRes cancelTrade(String req);
	public BaseMsg confirmPay(String req);
	public BaseMsg confirmReceived(String req);
	public BaseMsg findCurrentTrade(String req);
	public BaseMsg findHistoryMarket(String req);
	public BaseMsg findUserHistoryTrade(String req);
	public BaseMsg findTradeMarket(String req);
	
	public BaseMsg selUserAsset(String req);
	
	public BaseMsg getUserAsset(String req, Integer toUserId);
	
	public BaseMsg selTradeStatus(String req);
	
}
