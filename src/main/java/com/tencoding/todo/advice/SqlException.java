package com.tencoding.todo.advice;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class SqlException extends RuntimeException {

	private HttpStatus status; 
	
	public SqlException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}
	
}
