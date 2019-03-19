
package com.broctagon.exchangeadmin.service;

import com.broctagon.exchangeadmin.message.C2cEntrustListRes;
import com.broctagon.exchangeadmin.message.C2cResultRes;
import com.broctagon.exchangeadmin.message.C2cUserEntrustListRes;

/**
* @auther: Water
* @time: 15 Mar 2018 10:55:00
* 
*/

public interface C2cEntrustService {

	public C2cResultRes addEntrust(String req);
	public C2cResultRes cancelEntrust(String req);
	public C2cEntrustListRes queryEntrustList(String req);
	public C2cUserEntrustListRes queryUserEntrust(String req);
	public void freezonRes(String req);
}
