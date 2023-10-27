package com.tencoding.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tencoding.todo.dto.UserDTO;
import com.tencoding.todo.repository.entity.UserEntity;
import com.tencoding.todo.repository.mapper.UserRepository;

@Service // IoC 관리 대상 --> 싱글톤 객체 
public class UserService {

	// final 로 선언하는 이유 
	// 불변성 보장 : 한번 초기화 되면 상태 변경 불가능 
	// 스레드 안정상 : final 필드는 스레드 간 공유 될 때 안전
	// 의도 표현 - 불변성 이다. 
	private final UserRepository userRepository; 
	
	@Autowired // Autowired 명시적으로 선언해주게 좋다  
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/**
	 * 회원 가입 처리 
	 * @param userDTO
	 * @return
	 */
	public int signUp(UserDTO userDTO) {
		// 추가적인 작업 ... 생략 
		return userRepository.signUp(userDTO);
	}
	
	/**
	 * 로그인 처리  
	 * @param userDTO
	 * @return UserEntity
	 */
	public UserEntity signin(UserDTO userDTO) {
		return userRepository.signIn(userDTO);
	}
	
	
}
