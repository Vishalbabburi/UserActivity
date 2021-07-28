package com.miracle.UserActivity.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.miracle.UserActivity.entities.UserInfo;
import com.miracle.UserActivity.models.BenchEmployeeDetails;


@Repository
public interface EmployeeInfoDao extends JpaRepository<UserInfo, Integer> {
		
	public UserInfo findByUid(int id);
	
	//@Query("select u.uid,u.name,u.role from UserInfo u where u.name = ?1 and u.password=?2")
	//@Query("select new com.miracle.UserActivity.models.LoginResponse(u.uid, u.name,u.role) from UserInfo u where u.name = ?1 and u.password=?2")
	public UserInfo findByNameAndPassword(String name, String password);

	public boolean existsUserInfoByName(String name);

	public UserInfo findByName(String name);
	
	public UserInfo findByNameAndSecretKey(String name,String secretkey);
	
	@Query("select new com.miracle.UserActivity.models.BenchEmployeeDetails(u.uid,u.name) from UserInfo u where u.role=?1")
	public List<BenchEmployeeDetails> findBenchEmployeeDetails(String role);
}
