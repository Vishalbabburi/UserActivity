package com.miracle.UserActivity.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miracle.UserActivity.entities.UserInfo;


@Repository
public interface EmployeeInfoDao extends JpaRepository<UserInfo, Integer> {
		
	public UserInfo findByUid(int id);
	
	//@Query("select u.uid,u.name,u.role from UserInfo u where u.name = ?1 and u.password=?2")
	//@Query("select new com.miracle.UserActivity.models.LoginResponse(u.uid, u.name,u.role) from UserInfo u where u.name = ?1 and u.password=?2")
	public UserInfo findByNameAndPassword(String name, String password);
}
