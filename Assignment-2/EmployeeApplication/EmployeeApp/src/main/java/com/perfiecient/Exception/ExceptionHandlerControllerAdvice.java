package com.perfiecient.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.perficient.model.ErrorResponse;

@Component
@ControllerAdvice
public class ExceptionHandlerControllerAdvice{

	@ExceptionHandler(EmployeeNotFoundException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleEmployeeNotFound(EmployeeNotFoundException exception) {

		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),exception.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<ErrorResponse> handleSeviceException(final ServiceException exception) {

		ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage());	
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> handleBadRequestException(final BadRequestException exception) {

		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),exception.getMessage());	
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
}