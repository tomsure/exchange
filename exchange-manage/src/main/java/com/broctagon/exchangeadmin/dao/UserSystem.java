package com.broctagon.exchangeadmin.dao;



import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.broctagon.exchangeadmin.model.User;


public interface UserSystem extends CrudRepository<User,Integer>,JpaSpecificationExecutor<User>{
	
	
	/**
	 * 查询登录名是否存在，返回存在的id以及登录密码
	 * @param UserName  登录名
	 * @return
	 */
	@Query("SELECT new com.broctagon.exchangeadmin.model.User(ID, loginPassword) FROM User WHERE UserName = :UserName")
	public User Userlogin(@Param("UserName")String UserName);
	
	User findByUserName(@Param("UserName")String UserName);
	
	
	/**
	 * 修改密码
	 */
	@Modifying
	@Query("UPDATE User SET loginPassword = :loginPassword WHERE UserName = :UserName")
	int PwdModification(@Param("UserName") String UserName, @Param("loginPassword")String loginPassword);
	
	
}
