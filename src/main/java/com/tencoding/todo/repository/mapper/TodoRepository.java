package com.tencoding.todo.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencoding.todo.dto.TodoDTO;
import com.tencoding.todo.repository.entity.TodoEntity;

@Mapper
public interface TodoRepository {

 public List<TodoEntity> findAllList();
 public int createTodo(TodoDTO todoDTO);
 public TodoEntity findByIdTodo(Integer todoId);
 public int updateById(@Param("todoId") Integer todoId, @Param("todoDTO") TodoDTO todoDTO);
 public int deleteById(@Param("id") Integer id, @Param("userId") Integer userId);
	
}
