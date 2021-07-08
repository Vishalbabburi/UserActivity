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

import com.miracle.UserActivity.Dao.UserActivityDao;
import com.miracle.UserActivity.Dao.UserInfoDao;
import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;


@RestController
public class ActivityController {
	private Logger logger=LoggerFactory.getLogger(ActivityController.class);
	
	@Autowired
	UserActivityDao ActivityDao;
	
	@Autowired
	UserInfoDao  UserinfoDao;
	@GetMapping("/")
	public String home() {
		return "<h1> Welcome to user activity page</h1>";
	}

	@GetMapping("/findStatusByName/{name}")
	public List<UserActivity> findByName(@PathVariable("name") String thename){
		return ActivityDao.findByName(thename);
	}
	
	@GetMapping("/findStatusByDate/{date}")
	public List<UserActivity> findbydate(@PathVariable("date") String thedate){
		return ActivityDao.findByDate(thedate);
	}
	
	@PostMapping("/AddStatus/{id}")
	public UserActivity addstatus(@RequestBody UserActivity todayactivity, @PathVariable("id") int theid) {
		
		
			//get the UserInfo with particular id
		
		
		UserInfo theuser=UserinfoDao.findByUid(theid);
		//add activity to that user
		logger.info("about to add todayactivity to activities list in User info");
		theuser.add(todayactivity);
		logger.info("succesfully added to activities list");
		logger.info("about to save the activity to table");
		
			return	ActivityDao.save(todayactivity) ;	
	}
	@PutMapping("/editStatus")
	public UserActivity editStatus(@RequestBody UserActivity editedActivity) {
		return ActivityDao.save(editedActivity);
	}
	
	@DeleteMapping("/DeleteByName/{name}")
	public String deletebyname(@PathVariable("name") String thename) {
	
		ActivityDao.deleteByName(thename);
		return "All "+thename+" entries deleted successfully";
	}
	
	@DeleteMapping("/DeleteBydate/{date}")
	public String deletebydate(@PathVariable("date") String thedate) {
	
		ActivityDao.deleteByDate(thedate);
		return "All "+thedate+" entries deleted successfully";
	}
	@DeleteMapping("/DeleteAll")
	public String deleteallentries() {
		ActivityDao.deleteAll();
		return "All entries Deleted";
	}
	
	//for userinfo
	@PostMapping("/AddUserInfo")
	public UserInfo AddUser(@RequestBody UserInfo userinfo) {
		return UserinfoDao.save(userinfo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
