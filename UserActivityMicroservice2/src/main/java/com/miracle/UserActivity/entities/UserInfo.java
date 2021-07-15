package com.miracle.UserActivity.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	@Id
	@GeneratedValue
	private int uid;
	private String name;
	private String password;
	private String role;
	
//	@OneToMany(targetEntity=UserActivity.class,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
//	@JoinColumn(name="user_id",referencedColumnName="uid")
	@OneToMany(mappedBy="user_info_id",fetch=FetchType.LAZY)
	private List<UserActivity> useractivities;
	
	// method for adding user activities to a user for bi-directional mapping
		public void add(UserActivity useractivity) {
		if (useractivities==null) {
			useractivities=new ArrayList<UserActivity>();
			}
			useractivities.add(useractivity);
			useractivity.setUser_info_id(this);			
			}

}
