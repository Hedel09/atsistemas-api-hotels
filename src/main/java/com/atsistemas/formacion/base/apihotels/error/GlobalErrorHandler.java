package com.atsistemas.formacion.base.apihotels.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

	@ExceptionHandler(value = DateFormatException.class)
	public ResponseEntity<Object> handleDateFormatException(DateFormatException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NoAvailabilityException.class)
	public ResponseEntity<Object> handleNoAvailabilityException(NoAvailabilityException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.OK);
	}
	
	@ExceptionHandler(value = NullParamsException.class)
	public ResponseEntity<Object> handleNullParamsException(NullParamsException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = IncorrectDataException.class)
	public ResponseEntity<Object> handleIncorrectDataException(IncorrectDataException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
