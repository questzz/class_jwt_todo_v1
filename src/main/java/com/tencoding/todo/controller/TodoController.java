package com.tencoding.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
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

import com.tencoding.todo.advice.SqlException;
import com.tencoding.todo.dto.ResponseDTO;
import com.tencoding.todo.dto.TodoDTO;
import com.tencoding.todo.repository.entity.TodoEntity;
import com.tencoding.todo.service.TodoService;
import com.tencoding.todo.utils.JwtUtil;

@RestController
@RequestMapping("/todos")
public class TodoController {

	private final TodoService todoService;
	private final JwtUtil jwtUtil;

	@Autowired // 명시적 사용
	public TodoController(TodoService todoService, JwtUtil jwtUtil) {
		this.todoService = todoService;
		this.jwtUtil = jwtUtil;
	}

	// http://localhost:80/todos/all
	@GetMapping("/all")
	public ResponseEntity<List<TodoEntity>> getTodoList() {

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
	public ResponseEntity<?> postTodo(HttpServletRequest request,@RequestBody TodoDTO todoDTO) {

		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = ""; //
		token = jwtUtil.hasToken(authHeader);
		int userId  = jwtUtil.getUserIdFromToken(token);
	 	todoDTO.setUserId(userId); // 객체 상태 변경	
		int result = todoService.createTodo(todoDTO);
		if (result == 1) {
			return new ResponseEntity<>(1, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(-1, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> putTodoById(@PathVariable Integer id, @RequestBody TodoDTO dto, HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = jwtUtil.hasToken(authHeader);
		Integer userId = jwtUtil.getUserIdFromToken(token);
		dto.setUserId(userId);// 객체 상태 변경
		int result = todoService.updateTodoById(id, dto);
		System.out.println("-----------------------------------------");
		System.out.println("호출 확인 111111");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTodoById(@PathVariable Integer id, HttpServletRequest request) {
		String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		String token = jwtUtil.hasToken(authHeader);
		Integer userId =  jwtUtil.getUserIdFromToken(token);
		int result = todoService.deleteTodoById(id, userId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
