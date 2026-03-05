package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(CardNotFoundException.class)
	    public ResponseEntity<ApiResponse> handleCardNotFound(CardNotFoundException ex) {

	        ApiResponse response = ApiResponse.builder()
	                .message(ex.getMessage())
	                .data(null)
//	                .status(HttpStatus.NOT_FOUND.value())
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiResponse> handleGeneric(Exception ex) {

	        ApiResponse response = ApiResponse.builder()
	                .message("Internal Server Error")
	                .data(null)
//	                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}