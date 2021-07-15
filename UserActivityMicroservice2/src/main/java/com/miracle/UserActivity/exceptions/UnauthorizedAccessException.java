package com.miracle.UserActivity.exceptions;

public class UnauthorizedAccessException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2743166715770960395L;

	public UnauthorizedAccessException(String message){
        super(message);
    }
}
