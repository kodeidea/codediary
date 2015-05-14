package com.cd.validation;

public class UserAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 280785899094688864L;
	
	public UserAlreadyExistException() {
		super();
	}
	
	public UserAlreadyExistException(String message) {
		super(message);
	}
	
	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserAlreadyExistException(Throwable cause) {
		super(cause);
	}
}
