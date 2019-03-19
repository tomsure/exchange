
package com.broctagon.exchangeadmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.broctagon.exchangeadmin.dao.C2cCoinDao;
import com.broctagon.exchangeadmin.message.C2cCoinListMsgRes;
import com.broctagon.exchangeadmin.model.C2cCoinModel;
import com.broctagon.exchangeadmin.service.C2cCoinService;

/**
* @auther: Water
* @time: 16 Mar 2018 12:07:18
* 
*/

@Service
@Transactional
public class C2cCoinServiceImpl implements C2cCoinService{
	
	@Autowired
	private C2cCoinDao c2cCoinDao;
	
	public C2cCoinListMsgRes findC2cCoinList(){
		List<C2cCoinModel> c2cCoinList = c2cCoinDao.findAll();
		C2cCoinListMsgRes c2cCoinListMsg = new C2cCoinListMsgRes();
		c2cCoinListMsg.setCoinList(c2cCoinList);
		return c2cCoinListMsg;
	}


}
