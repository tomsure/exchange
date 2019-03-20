package com.broctagon.exchangeadmin.dao;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.broctagon.exchangeadmin.model.UserAssets;


public interface UserAssetsSystem extends CrudRepository<UserAssets,Integer>,JpaSpecificationExecutor<UserAssets>{
	
	
	
	
	
}
