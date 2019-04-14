package com.qs.telotengo.user.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qs.telotengo.user.dto.ErrorDto;
import com.qs.telotengo.user.exception.ValidationExceptions;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(ValidationExceptions.class)
	public HttpEntity<ErrorDto> handleValidations(ValidationExceptions e) {
		
		ErrorDto error =new ErrorDto();
		error.setInternalCode(e.getCodError());
		error.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDto>(error, e.getStatus());
	} 
}
