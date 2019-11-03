package com.spring.restful.orders.app.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ValidateErrors implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {

	}

}
