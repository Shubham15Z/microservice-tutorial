package com.srz.user.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.srz.user.service.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex)
	{
		String message = ex.getMessage();
		ApiResponse response = ApiResponse.builder().mesage(message).SUCCESS(true).status(HttpStatus.NOT_FOUND).build();
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
	}
}
