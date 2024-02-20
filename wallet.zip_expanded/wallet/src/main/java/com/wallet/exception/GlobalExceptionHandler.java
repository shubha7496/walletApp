package com.wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
		
	@ExceptionHandler(InsufficientBalanceException.class)
	public ProblemDetail InsufficientBalanceException(InsufficientBalanceException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
	}


}
