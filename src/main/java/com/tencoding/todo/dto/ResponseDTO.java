package com.tencoding.todo.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
	String token;
	int code; 
	String message; 
	T data; 
}
