package com.spring.restful.orders.app.validations;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {
	private List<ApiFieldError> apiFieldErrors;

	public ValidationErrors() {
		apiFieldErrors = new ArrayList<ApiFieldError>();
	}

	public void addFieldError(String path, String message) {
		ApiFieldError apiFieldError = new ApiFieldError(path, message);
		apiFieldErrors.add(apiFieldError);
	}

	
	public List<ApiFieldError> getApiFieldErrors() {
		return apiFieldErrors;
	}

	
	public void setApiFieldErrors(List<ApiFieldError> apiFieldErrors) {
		this.apiFieldErrors = apiFieldErrors;
	}

}
