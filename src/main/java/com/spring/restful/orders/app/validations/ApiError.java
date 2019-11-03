package com.spring.restful.orders.app.validations;

public class ApiError {

	private String code;
	private String message;
	private ValidationErrors errors;
	public ApiError() {
	}
	public ApiError(String code, String message, ValidationErrors errors) {
		super();
		this.code = code;
		this.message = message;
		this.errors = errors;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ValidationErrors getErrors() {
		return errors;
	}
	
	public void setErrors(ValidationErrors errors) {
		this.errors = errors;
	}
	
	
}
