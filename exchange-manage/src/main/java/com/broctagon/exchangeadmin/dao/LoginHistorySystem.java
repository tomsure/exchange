package com.broctagon.exchangeadmin.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.broctagon.exchangeadmin.model.LoginHistory;

public interface LoginHistorySystem extends CrudRepository<LoginHistory,Integer>,JpaSpecificationExecutor<LoginHistory>{

}
