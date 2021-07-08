package com.miracle.UserActivity.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miracle.UserActivity.entities.UserInfo;

public interface UserInfoDao extends JpaRepository<UserInfo, Integer> {
		
	public UserInfo findByUid(int id);
}
