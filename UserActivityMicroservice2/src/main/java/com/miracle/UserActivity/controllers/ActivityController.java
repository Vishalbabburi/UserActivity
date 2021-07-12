package com.miracle.UserActivity.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.UserActivity.dao.UserActivityDao;
import com.miracle.UserActivity.dao.UserInfoDao;
import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;


@RestController
public class ActivityController {
	private Logger logger=LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	UserActivityDao activityDao;
	
	@Autowired
	UserInfoDao userInfoDao;

//	@GetMapping("/")
//	public String home() {
//		return "<h1> Welcome to user activity page</h1>";
//	}

	@GetMapping("/findStatusByName/{name}")
	public List<UserActivity> findByName(@PathVariable("name") String name){
		return activityDao.findByName(name);
	}
	
	@GetMapping("/findStatusByDate/{date}")
	public List<UserActivity> findbydate(@PathVariable("date") String date){
		return activityDao.findByDate(date);
	}
	
	@PostMapping("/AddStatus/{id}")
	public UserActivity addstatus(@RequestBody UserActivity todayActivity, @PathVariable("id") int id) {

		//get the UserInfo with particular id
		UserInfo theuser= userInfoDao.findByUid(id);
		//add activity to that user
		logger.info("about to add todayActivity to activities list in User info");
		theuser.add(todayActivity);
		logger.info("succesfully added to activities list");
		logger.info("about to save the activity to table");
		
		return	activityDao.save(todayActivity) ;
	}
	@PutMapping("/editStatus")
	public UserActivity editStatus(@RequestBody UserActivity editedActivity) {
		return activityDao.save(editedActivity);
	}
	
	@DeleteMapping("/DeleteByName/{name}")
	public String deletebyname(@PathVariable("name") String name) {
	
		activityDao.deleteByName(name);
		return "All "+name+" entries deleted successfully";
	}
	
	@DeleteMapping("/DeleteBydate/{date}")
	public String deletebydate(@PathVariable("date") String date) {
	
		activityDao.deleteByDate(date);
		return "All "+date+" entries deleted successfully";
	}
	@DeleteMapping("/DeleteAll")
	public String deleteallentries() {
		activityDao.deleteAll();
		return "All entries Deleted";
	}
	
	//for userInfo
	@PostMapping("/AddUserInfo")
	public UserInfo AddUser(@RequestBody UserInfo userInfo) {
		return userInfoDao.save(userInfo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
