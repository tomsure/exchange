
package com.broctagon.exchangeadmin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.broctagon.exchangeadmin.model.C2cCoinModel;

/**
* @auther: Water
* @time: 8 Mar 2018 18:22:00
* 
*/

@Mapper
public interface C2cCoinDao {

	public List<C2cCoinModel> findAll();
	
	
}
