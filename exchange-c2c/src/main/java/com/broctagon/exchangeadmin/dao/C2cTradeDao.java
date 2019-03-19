
package com.broctagon.exchangeadmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.broctagon.exchangeadmin.model.C2cEntrustModel;
import com.broctagon.exchangeadmin.model.C2cMarketModel;
import com.broctagon.exchangeadmin.model.C2cTradeAccountModel;
import com.broctagon.exchangeadmin.model.C2cTradeDetailModel;
import com.broctagon.exchangeadmin.model.C2cTradeModel;
import com.broctagon.exchangeadmin.model.C2cTradeVolumeModel;

/**
* @auther: Water
* @time: 13 Mar 2018 14:34:43
* 
*/

@Mapper
public interface C2cTradeDao {

	public List<C2cTradeModel> findAll();
	public C2cTradeModel findById(int id);
	public Double findLastPrice(String coinName);
	public int save(C2cTradeModel c2cTradeModel);
	public int updateTradeStatus(C2cTradeModel c2cTradeModel);
	public int cancelTrade(C2cTradeModel c2cTradeModel);
	
	public List<C2cMarketModel> findHistoryMarket(String coinName);
	public List<C2cMarketModel> findUserHistoryTrade(C2cEntrustModel c2cEnturstModel);
	public List<C2cTradeDetailModel> findCurrentTrade(C2cEntrustModel c2cEnturstModel);
	
	public List<C2cTradeDetailModel> findCurrentMerchantTrade(C2cEntrustModel c2cEnturstModel);
	public List<C2cMarketModel> findMerchantHistoryTrade(C2cEntrustModel c2cEnturstModel);
	public List<C2cTradeVolumeModel> findTradeVolume(String coinName);
	public Double findLastTrade(String coinName);
	
	public C2cTradeAccountModel findTradeDetailById(int id);
	
	public Map<String,Object> selectBanklist(@Param(value = "userId") int userId);
	
	
	public Map<String,Object> selectUserId(@Param(value = "id") int id);
	
}
