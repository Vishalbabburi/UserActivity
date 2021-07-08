package com.miracle.UserActivity.entities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityId implements Serializable{

	/**
	 * This Class main purpose is to act as Composite key for UserActivity table
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String date;
}
