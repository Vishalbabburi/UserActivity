package com.miracle.UserActivity.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miracle.UserActivity.dao.EmployeeActivityDao;
import com.miracle.UserActivity.dao.EmployeeInfoDao;
import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;
import com.miracle.UserActivity.exceptions.EmployeeNotFoundException;
import com.miracle.UserActivity.exceptions.UnauthorizedAccessException;
import com.miracle.UserActivity.models.BenchEmployeeDetails;
import com.miracle.UserActivity.models.LoginRequest;
import com.miracle.UserActivity.models.LoginResponse;
import com.miracle.UserActivity.models.PasswordResetRequest;

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

    public List<UserInfo> findAllUser() {
        return employeeInfoDao.findAll();
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
            logger.info("successfully added to activities list");
            logger.info("about to save the activity to table");
            return	EmployeeActivityDao.save(todayActivity) ;
        } else{
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
		return EmployeeActivityDao.findStatusByNameAndDate(name, date);
	}

	public List<UserActivity> findByUid(int id) {
		 UserInfo userinfo=employeeInfoDao.findByUid(id);
		 return userinfo.getUseractivities();
	}

	public LoginResponse authenticate(LoginRequest loginRequest) {
		String name=loginRequest.getName();
		String password=loginRequest.getPassword();
		
		UserInfo user=employeeInfoDao.findByNameAndPassword(name,password);
		if(user==null) throw new UnauthorizedAccessException("UNAUTHORIZED ACCESS");
		LoginResponse loginResponse=new LoginResponse(user.getUid(),user.getName(),user.getRole());
		return loginResponse;
	}

	public boolean resetpassword(PasswordResetRequest passwordresetrequest) {
		String name=passwordresetrequest.getName();
		String secretkey=passwordresetrequest.getSecretkey();
		String newPassword=passwordresetrequest.getPassword();
		//UserInfo userinfo=employeeInfoDao.findByName(name);
		UserInfo userinfo=employeeInfoDao.findByNameAndSecretKey(name,secretkey);
		if(userinfo==null) return false;
		userinfo.setPassword(newPassword);
		employeeInfoDao.save(userinfo);
		return true;
	}

	public List<BenchEmployeeDetails> getAllBenchEmployeeDetails() {	
		return employeeInfoDao.findBenchEmployeeDetails("Bench_Employee");
	}



}
