package com.miracle.UserActivity.services;

import com.miracle.UserActivity.dao.EmployeeActivityDao;
import com.miracle.UserActivity.dao.EmployeeInfoDao;
import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;
import com.miracle.UserActivity.exceptions.EmployeeNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeActivityServices {

    private Logger logger= LoggerFactory.getLogger(EmployeeActivityServices.class);

    @Autowired
    EmployeeActivityDao EmployeeActivityDao;

    @Autowired
    EmployeeInfoDao employeeInfoDao;

    public List<UserActivity> findByName(String name){
        return EmployeeActivityDao.findByName(name);
    }

    public List<UserActivity> findByDate(String date){
        return EmployeeActivityDao.findByDate(date);
    }

    public UserActivity createStatus(UserActivity todayActivity, int id) throws EmployeeNotFoundException{
        //get the UserInfo with particular id
    	UserInfo user= employeeInfoDao.findById(id).orElse(null);
        if(user!=null){
            logger.info("about to add todayActivity to activities list in User info");
            user.add(todayActivity);
            logger.info("succesfully added to activities list");
            logger.info("about to save the activity to table");
            return	EmployeeActivityDao.save(todayActivity) ;
        } else{
//            return new EmployeeNotFoundException("Employee with id : "+id+"does not exist.");
        	throw new EmployeeNotFoundException("Employee with id : "+id+"does not exist.");
        }
    }

    public UserActivity editStatus(UserActivity editedActivity){
        return EmployeeActivityDao.save(editedActivity);
    }

    public String deleteByName(String name){
        EmployeeActivityDao.deleteByDate(name);
        return "All "+name+" entries deleted successfully";
    }

    public String deleteByDate(String date) {
        EmployeeActivityDao.deleteByDate(date);
        return "All "+date+" entries deleted successfully";
    }

    public String deleteAll() {
        EmployeeActivityDao.deleteAll();
        return "All entries Deleted";
    }

    public UserInfo createUser(UserInfo userInfo) {
        return employeeInfoDao.save(userInfo);
    }

	public List<UserActivity> findStatusByNameAndDate(String name, String date) {
		// TODO Auto-generated method stub
		return EmployeeActivityDao.findStatusByNameAndDate(name, date);
	}


}
