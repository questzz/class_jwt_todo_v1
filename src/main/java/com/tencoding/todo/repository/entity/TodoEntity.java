package com.tencoding.todo.repository.entity;

import lombok.Data;

@Data
public class TodoEntity {
	private Integer id;
	private String titie;
	private boolean completed;
}
