package com.miracle.UserActivity.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miracle.UserActivity.entities.UserInfo;

import java.util.Optional;

public interface EmployeeInfoDao extends JpaRepository<UserInfo, Integer> {
		
	public Optional findByUid(int id);
}
