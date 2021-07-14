package com.miracle.UserActivity.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.miracle.UserActivity.entities.UserInfo;


@Repository
public interface EmployeeInfoDao extends JpaRepository<UserInfo, Integer> {
		
	public UserInfo findByUid(int id);
}
