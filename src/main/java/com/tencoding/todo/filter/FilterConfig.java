package com.tencoding.todo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// 어노테이션은 해당 클래스가 스프링 프레임워크의 설정 정보를 담는 클래스를 나타낸다. 
// 이 Configuration @Component을 상속 받고 있다. 
// 이 클래스에 내부에서 Bean 객체를 더 생성해야 할 때 사용
@Slf4j
//@Configuration 
public class FilterConfig {
	
	// 1. 우리가 정의한 JWT 관련된 필터 동작 객체를 생성자 의존 주의 받는다. 
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	// 특정 메서들 만들어서 URI 패턴을 등록 처리
	@Bean
	public FilterRegistrationBean<JwtRequestFilter> loggingFilter() {
		log.error("스프링 부트 구동시 초기화 확인 - 1");
		
		FilterRegistrationBean<JwtRequestFilter> registrationBean 
					= new FilterRegistrationBean<>();
		registrationBean.setFilter(jwtRequestFilter);
		registrationBean.addUrlPatterns("/todos/*");
		return registrationBean;
	}
	
	
	
}
