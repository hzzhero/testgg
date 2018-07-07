package com.example.demo.util;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ServiceException extends Exception {

	public ServiceException() {
		super("业务层异常!");  
	}
	
	public ServiceException(String str,Exception e) {
		super(str,e);
	}

	public ServiceException(String format) {
		super(format);  
	}

}
