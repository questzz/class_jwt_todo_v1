package com.tencoding.todo.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.tencoding.todo.dto.UserDTO;
import com.tencoding.todo.repository.entity.UserEntity;

@Mapper
public interface UserRepository {

	// 회원 가입
	int signUp(UserDTO userDTO);
	// 로그인 
	UserEntity signIn(UserDTO userDTO);  
}
