package com.miracle.UserActivity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.miracle.UserActivity.entities.UserActivity;

public interface EmployeeActivityDao extends JpaRepository<UserActivity, Integer> {

	public List<UserActivity> findByName(String name);

//	public List<UserActivity> findByStatus(String status);

	public List<UserActivity> findByDate(String date);

	@Transactional
	@Modifying(clearAutomatically = true)
	public void deleteByName(String name);

	@Transactional
	@Modifying(clearAutomatically = true)
	public void deleteByDate(String date);

	public List<UserActivity> findStatusByNameAndDate(String name, String date);

}
