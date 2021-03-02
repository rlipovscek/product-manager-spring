package com.compasso.desafio.productcatalog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.compasso.desafio.productcatalog.exceptions.EntityException;
import com.compasso.desafio.productcatalog.exceptions.ResponseErrorModel;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

@Component
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
	private ResponseEntity<ResponseErrorModel> format(Exception e, HttpStatus internalServerError, String message) {
		log.error(e.getMessage());

		if (e != null && e.getMessage() != null && message == null) {
			message = e.getMessage();
		}

		ResponseErrorModel response = ResponseErrorModel.builder().statusCode(internalServerError.value())
				.message(message).build();
		return ResponseEntity.status(internalServerError.value()).body(response);
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<ResponseErrorModel> handlerException(
			Exception e) {
		return format(e, HttpStatus.BAD_REQUEST, null);
	}
	

	@ExceptionHandler(InvalidFormatException.class)
	protected ResponseEntity<ResponseErrorModel> handlerInvalidFormatExceptionn(
			EntityException e) {
		return format(e, HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
	}
	
	@ExceptionHandler(EntityException.class)
	protected ResponseEntity<ResponseErrorModel> handlerEntityException(
			EntityException e) {
		return format(e, HttpStatus.NOT_FOUND, null);
	}


	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ResponseErrorModel> handlerMethodArgumentNotValidException(
			MethodArgumentNotValidException e) {
		
		String message = null;
		
		for (ObjectError oe : e.getBindingResult().getAllErrors()) {
			message = oe.getDefaultMessage();
			break;

		}


		return format(e, HttpStatus.BAD_REQUEST, message);
	}

}
