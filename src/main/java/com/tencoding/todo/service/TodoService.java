package com.tencoding.todo.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tencoding.todo.advice.SqlException;
import com.tencoding.todo.dto.TodoDTO;
import com.tencoding.todo.repository.entity.TodoEntity;
import com.tencoding.todo.repository.mapper.TodoRepository;

@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public TodoService(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	public List<TodoEntity> readAllTodo() {
		try {
			return todoRepository.findAllList();
		} catch (Exception e) {
			throw new SqlException("xxxx", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	public int createTodo(TodoDTO dto) {
		// 임시 데이터 처리
		//dto.setUserId(1);
		return todoRepository.createTodo(dto);
	}
	
	public TodoEntity readTodById(Integer todoId) {
		return todoRepository.findByIdTodo(todoId);
	}

	public int updateTodoById(Integer todoId, TodoDTO todoDTO) {
	
		return todoRepository.updateById(todoId, todoDTO);
	}
	
	public int deleteTodoById(Integer id, Integer userId) {
		return todoRepository.deleteById(id, userId);		
	}
	
}
