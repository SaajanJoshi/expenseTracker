package com.restapi.expensetracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EtResourseNotFoundException extends RuntimeException{
	
	public EtResourseNotFoundException(String message) {
		super(message);
	}
}
