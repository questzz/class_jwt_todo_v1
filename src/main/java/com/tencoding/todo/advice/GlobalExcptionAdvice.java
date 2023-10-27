package com.tencoding.todo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExcptionAdvice {

	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		log.error("-------------------------");
		log.error(e.getClass().getName());
		log.error(e.getMessage());
	}
	
	@ExceptionHandler(SqlException.class)
	public ResponseEntity<String> sqlError(SqlException e) {	
		log.error(e.toString());
		return new ResponseEntity<>("ashdflkhasdlkfhaslkdhfashdf", e.getStatus());
	}
	
	
	
	
}
