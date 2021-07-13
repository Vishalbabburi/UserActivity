package com.miracle.UserActivity.controllers;

import java.util.List;

import com.miracle.UserActivity.services.UserActivityServices;
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

import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;


@RestController
public class ActivityController {
	private Logger logger=LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	UserActivityServices userActivityServices;

//	@GetMapping("/")
//	public String home() {
//		return "<h1> Welcome to user activity page</h1>";
//	}

	@GetMapping("/findStatusByName/{name}")
	public List<UserActivity> findByName(@PathVariable("name") String name){
		return userActivityServices.findByName(name);
	}
	
	@GetMapping("/findStatusByDate/{date}")
	public List<UserActivity> findbydate(@PathVariable("date") String date){
		return userActivityServices.findByDate(date);
	}
	@GetMapping("/findStatusByNameAndDate/{name}/{date}")
	public List<UserActivity> findStatusByNameAndDate(@PathVariable("name") String name,
													@PathVariable("date") String date){
			return userActivityServices.findStatusByNameAndDate(name,date);
		
	}
	@PostMapping("/AddStatus/{id}")
	public UserActivity addstatus(@RequestBody UserActivity todayActivity, @PathVariable("id") int id) {
		return	userActivityServices.createStatus(todayActivity,id) ;
	}
	@PutMapping("/editStatus")
	public UserActivity editStatus(@RequestBody UserActivity editedActivity) {
		return userActivityServices.editStatus(editedActivity);
	}
	
	@DeleteMapping("/DeleteByName/{name}")
	public String deletebyname(@PathVariable("name") String name) {
		return userActivityServices.deleteByName(name);
	}
	
	@DeleteMapping("/DeleteBydate/{date}")
	public String deletebydate(@PathVariable("date") String date) {
		return userActivityServices.deleteByDate(date);
	}

	@DeleteMapping("/DeleteAll")
	public String deleteallentries() {
		return userActivityServices.deleteAll();
	}

	@PostMapping("/AddUserInfo")
	public UserInfo addUser(@RequestBody UserInfo userInfo) {
		return userActivityServices.createUser(userInfo);
	}

}
