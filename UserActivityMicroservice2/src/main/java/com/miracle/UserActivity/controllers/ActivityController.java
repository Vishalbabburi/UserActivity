package com.miracle.UserActivity.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.UserActivity.entities.UserActivity;
import com.miracle.UserActivity.entities.UserInfo;
import com.miracle.UserActivity.models.BenchEmployeeDetails;
import com.miracle.UserActivity.models.LoginRequest;
import com.miracle.UserActivity.models.LoginResponse;
import com.miracle.UserActivity.models.PasswordResetRequest;
import com.miracle.UserActivity.security.JWTTokenHelper;
import com.miracle.UserActivity.security.MyUserDetails;
import com.miracle.UserActivity.services.EmployeeActivityServices;

@CrossOrigin
@RestController
public class ActivityController {

	@Autowired
	EmployeeActivityServices employeeActivityServices;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	JWTTokenHelper jWTTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;

//	@GetMapping("/")
//	public String home() {
//		return "<h1> Welcome to user activity page</h1>";
//	}
	@PostMapping("/login")
	public LoginResponse authenticate(@RequestBody LoginRequest loginRequest) {
		return employeeActivityServices.authenticate(loginRequest);
	}
	@GetMapping("/findAllUser")
	public List<UserInfo> findAllUser(){
		return employeeActivityServices.findAllUser();
	}
	@GetMapping("/findStatusByName/{name}")
	public List<UserActivity> findByName(@PathVariable("name") String name){
		return employeeActivityServices.findByName(name);
	}
	@GetMapping("/findStatusByid/{id}")
	public List<UserActivity> findStatusByid(@PathVariable("id") int uid){
		return employeeActivityServices.findByUid(uid);
	}
	@GetMapping("/findStatusByDate/{date}")
	public List<UserActivity> findbydate(@PathVariable("date") String date){
		return employeeActivityServices.findByDate(date);
	}
	@GetMapping("/findStatusByNameAndDate/{name}/{date}")
	public List<UserActivity> findStatusByNameAndDate(@PathVariable("name") String name,
													@PathVariable("date") String date){
			return employeeActivityServices.findStatusByNameAndDate(name,date);
		
	}
	@PostMapping("/AddStatus/{id}")
	public ResponseEntity<UserActivity> addstatus(@RequestBody UserActivity todayActivity, @PathVariable("id") int id) {
		return	new ResponseEntity<>(employeeActivityServices.createStatus(todayActivity,id), HttpStatus.OK);
	}
	@PutMapping("/editStatus/{uid}")
	public UserActivity editStatus(@RequestBody UserActivity editedActivity, @PathVariable("uid") int uid) {
		return employeeActivityServices.editStatus(editedActivity,uid);
	}
//	@PutMapping("/editStatus")
//	public UserActivity editStatus(@RequestBody UserActivity editedActivity){
//		return employeeActivityServices.editStatus(editedActivity);
//	}
	
	@DeleteMapping("/DeleteByName/{name}")
	public String deletebyname(@PathVariable("name") String name) {
		return employeeActivityServices.deleteByName(name);
	}
	@DeleteMapping("/DeleteById/{id}")
	public String deletebyname(@PathVariable("id") int id) {
		return employeeActivityServices.deleteById(id);
	}
	
	@DeleteMapping("/DeleteBydate/{date}")
	public String deletebydate(@PathVariable("date") String date) {
		return employeeActivityServices.deleteByDate(date);
	}

	@DeleteMapping("/DeleteAll")
	public String deleteallentries() {
		return employeeActivityServices.deleteAll();
	}

	@PostMapping("/RegisterUser")
	public UserInfo addUser(@RequestBody UserInfo userInfo) {
		return employeeActivityServices.createUser(userInfo);
	}
	
	@PostMapping("/ResetPassword")
	public boolean resetPassword(@RequestBody PasswordResetRequest passwordresetrequest) {		 
		return employeeActivityServices.resetpassword(passwordresetrequest);
	}
	@GetMapping("/GetAllBenchEmployeeDetails")
	public List<BenchEmployeeDetails> getAllBenchEmployeeDetails(){
		return employeeActivityServices.getAllBenchEmployeeDetails();
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getName(), authenticationRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(authentication.getPrincipal());
		MyUserDetails user=(MyUserDetails)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		List<?> roles=(List<?>)user.getAuthorities();
		LoginResponse response=new LoginResponse();
		response.setId(user.getId());
		response.setName(user.getUsername());
		response.setRole(roles.get(0).toString());
		response.setToken(jwtToken);
		

		return ResponseEntity.ok(response);
	}
	
	
	
	
		
}
