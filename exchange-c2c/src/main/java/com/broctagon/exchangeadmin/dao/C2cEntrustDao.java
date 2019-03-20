
package com.broctagon.exchangeadmin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.broctagon.exchangeadmin.model.C2cEntrustModel;

/**
* @auther: Water
* @time: 7 Mar 2018 15:09:22
* 
*/

@Mapper
public interface C2cEntrustDao {
	
	public List<C2cEntrustModel> findAll();
	public C2cEntrustModel findById(int id);
	public Integer findLastId();
	public List<Map<String,Object>> findByCoinAndUserId(C2cEntrustModel c2cEntrustModel);
	public List<C2cEntrustModel> findSellByCoin(String coin);
	public List<C2cEntrustModel> findBuyByCoin(String coin);
	public int save(C2cEntrustModel c2cEntrustModel);
	public int subtractAmount(C2cEntrustModel c2cEntrustModel);
	public int updateStatus(C2cEntrustModel c2cEntrustModel);
	public int deleteByEntrustId(int id);
	
	
	public int updateAssets(@Param(value = "userId")int userId,@Param(value = "count")double count,@Param(value = "coinName")String coinName);
	
	
	public int updateAssets1(@Param(value = "userId")int userId,@Param(value = "count")double count,@Param(value = "coinName")String coinName);
	
	public int updateAmount(C2cEntrustModel c2cEntrustModel);
	
}
