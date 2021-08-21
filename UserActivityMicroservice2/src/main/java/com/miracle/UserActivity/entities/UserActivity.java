package com.miracle.UserActivity.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivity {
	
	private String name;
	
	@Id
	@GeneratedValue
	private int id;
	private String date;
	
	private String status;
	
	private String task;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="uid")
	@JsonIgnore
		private UserInfo user_info_id;

}
