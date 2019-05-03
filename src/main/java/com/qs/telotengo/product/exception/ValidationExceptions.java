package com.qs.telotengo.product.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ValidationExceptions extends Exception {

	private HttpStatus status;
	private String codError;
	
	public ValidationExceptions(String message, String codError){
		super(message);
		this.codError = codError;
	}
	public ValidationExceptions(String message){
		super(message);
	}
	
	public ValidationExceptions(String codError, String message, HttpStatus status){
		super(message);
		this.status = status;
		this.codError = codError;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	public String getCodError() {
		return codError;
	}
	
}
