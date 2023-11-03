package com.tencoding.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest.Headers;

import com.tencoding.todo.dto.UserDTO;
import com.tencoding.todo.repository.entity.UserEntity;
import com.tencoding.todo.service.UserService;
import com.tencoding.todo.utils.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	// 코드 추가 
	private final JwtUtil jwtUtil;
	
	// 코드 수정
	@Autowired	
	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	// 주소 설계 
	// 회원 가입 요청 -- form, HTTP Message body
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserDTO userDTO) {
		// 데이터 유효성 검사- 생략  
		int result = userService.signUp(userDTO); // 201 , 200, 404  
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}  
	
	
	// 로그인 요청 --> 보안상 이유
	@PostMapping("/sign-in")
	public ResponseEntity<?> signin(@RequestBody UserDTO userDTO) {
		// 아이디만 확인 
		// 비번까지 확인 
		UserEntity user = userService.signin(userDTO); 
		// 세션 처리 ---> JWT
		if(user != null) {
			
			String token = jwtUtil.generateToken(user.getEmail(), user.getUserId());
			// 헤더 셋팅 
			HttpHeaders headers = new HttpHeaders();
			// JWT 헤더는 약속 Bearer , 으로 반드시 시작 해야 한다. 
			headers.add("Authorization", "Bearer " + token);
			System.out.println("token : " + token);
			return ResponseEntity.ok().headers(headers).body(user);
		} else {
			return new ResponseEntity<>("로그인 실패", HttpStatus.UNAUTHORIZED);
		}
	}
	
	// TEST
	// http://localhost:80/user/token-test
	@GetMapping("/token-test")
	public String testToken() {
		// eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyRW1haWwiOiJ0ZXN0QG5hdmVyLmNvbSIsInVzZXJJZCI6MTAsImV4cCI6MTY5ODQ3NDAxNn0.3SGGdFMME3FCVDNrFaQLOlqzN-i28khCAnG65PJe49BLdm2NdHK5AckXj-3AqQAV-Y10idjhdRiIOz2PR-OrMg
		return jwtUtil.generateToken("test@naver.com", 10);
	}
}
