package com.miracle.UserActivity.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.miracle.UserActivity.entities.UserActivity;

public interface UserActivityDao extends JpaRepository<UserActivity, Integer> {
		
	//naming convention is important 
			public List<UserActivity> findByName(String name);
			
			
			
			public List<UserActivity> findByStatus(String status);
			
			public List<UserActivity> findByDate(String date);
			
			public List<UserActivity> findByNameAndDate(String name,String date);
			
			@Transactional
			@Modifying(clearAutomatically=true)
			public void deleteByName(String name);
			
			@Transactional
			@Modifying(clearAutomatically=true)
			public void deleteByDate(String thedate);
			

		
}
