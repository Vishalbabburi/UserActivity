package com.miracle.UserActivity.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2851006316184294206L;

	public EmployeeNotFoundException(String message){
        super(message);
    }
}
