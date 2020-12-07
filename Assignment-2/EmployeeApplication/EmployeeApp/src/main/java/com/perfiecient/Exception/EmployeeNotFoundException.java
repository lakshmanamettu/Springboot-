package com.perfiecient.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	

	public EmployeeNotFoundException(String message) {
		super(message);

	}


	@Override
	public String toString() {
		return "EmployeeNotFoundException [message=" + message + "]";
	}

}
