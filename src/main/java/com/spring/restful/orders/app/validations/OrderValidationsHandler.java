package com.spring.restful.orders.app.validations;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderValidationsHandler {

	private MessageSource messageSource;

	@Autowired
	public OrderValidationsHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<ApiError> handleInvalidNumbers(NumberFormatException ex) {
		String result = ex.getLocalizedMessage();
		System.out.println("NFE "+result);
		ValidationErrors errors  = new ValidationErrors();
		//errors.addFieldError("orderId", "invalid number");
		errors.addFieldError("orderId", result);
		ApiError apiError = new ApiError("123-400", "Invalid Input Format", errors);
		return Arrays.asList(apiError);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public List<ApiError> handleEmptyResultSet(EmptyResultDataAccessException ex) {
		String result = ex.getLocalizedMessage();
		System.out.println("ERDAE "+result);
		ValidationErrors errors = new ValidationErrors();
		//errors.addFieldError("orderId", "task not found");
		errors.addFieldError("orderId", result);
		ApiError apiError = new ApiError("123-404", "Order id does not exist", errors);

		return Arrays.asList(apiError);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrors processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		return processFieldErrors(fieldErrors);
	}

	private ValidationErrors processFieldErrors(List<FieldError> fieldErrors) {
		ValidationErrors validationErrors = new ValidationErrors();
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			validationErrors.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		return validationErrors;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}
		return localizedErrorMessage;
	}
}
