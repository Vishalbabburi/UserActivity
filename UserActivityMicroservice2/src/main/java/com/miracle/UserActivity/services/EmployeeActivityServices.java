package com.miracle.UserActivity.services;

import com.miracle.UserActivity.dao.EmployeeActivityDao;
import com.miracle.UserActivity.dao.EmployeeInfoDao;
import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeActivityServices {

    private Logger logger= LoggerFactory.getLogger(EmployeeActivityServices.class);

    @Autowired
    EmployeeActivityDao activityDao;

    @Autowired
    EmployeeInfoDao employeeInfoDao;

    public List<UserActivity> findByName(String name){
        return activityDao.findByName(name);
    }

    public List<UserActivity> findByDate(String date){
        return activityDao.findByDate(date);
    }

    public UserActivity createStatus(UserActivity todayActivity, int id){
        //get the UserInfo with particular id
        UserInfo user= employeeInfoDao.findByUid(id);
        //add activity to that user
        logger.info("about to add todayActivity to activities list in User info");
        user.add(todayActivity);
        logger.info("succesfully added to activities list");
        logger.info("about to save the activity to table");

        return	activityDao.save(todayActivity) ;
    }

    public UserActivity editStatus(UserActivity editedActivity){
        return activityDao.save(editedActivity);
    }

    public String deleteByName(String name){
        activityDao.deleteByDate(name);
        return "All "+name+" entries deleted successfully";
    }

    public String deleteByDate(String date) {
        activityDao.deleteByDate(date);
        return "All "+date+" entries deleted successfully";
    }

    public String deleteAll() {
        activityDao.deleteAll();
        return "All entries Deleted";
    }

    public UserInfo createUser(UserInfo userInfo) {
        return employeeInfoDao.save(userInfo);
    }


}
