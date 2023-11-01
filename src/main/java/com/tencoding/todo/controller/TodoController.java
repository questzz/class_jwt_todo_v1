package com.tencoding.todo.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tencoding.todo.dto.ResponseDTO;
import com.tencoding.todo.dto.TodoDTO;
import com.tencoding.todo.repository.entity.TodoEntity;
import com.tencoding.todo.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {
	
	private final TodoService todoService;
	
	@Autowired // 명시적 사용
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping("/test")
	public int todoTeste() {
		System.out.println("9999999999");
		return 1;
	}

	// http://localhost:80/todos/all
	@GetMapping("/all")
	public ResponseEntity<List<TodoEntity>> getTodoList() {
		// TODO - SQL 오류 발생 시켜 놓았음 !!!
		List<TodoEntity> todos = todoService.readAllTodo();
		return new ResponseEntity<>(todos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseDTO<?> getTodoById(@PathVariable Integer todoId) {
		TodoEntity todo = todoService.readTodById(todoId);
		
		ResponseDTO<TodoEntity> responseDto = new ResponseDTO<>();
		responseDto.setCode(1);
		responseDto.setMessage("정상처리 되었습니다");
		responseDto.setData(todo); 
		
		if (todo != null) {
			return responseDto;
		} else {
			return responseDto;
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> postTodo() {
		System.out.println("11111111111");
		//int result = todoService.createTodo(todoDTO);
		return new ResponseEntity<>("TEST", HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> putTodoById(@PathVariable Integer id, @RequestBody TodoDTO dto) {
		int result = todoService.updateTodoById(id, dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable Integer id) {
		int result = todoService.deleteTodoById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
