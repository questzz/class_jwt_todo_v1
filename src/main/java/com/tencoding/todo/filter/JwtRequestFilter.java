package com.tencoding.todo.filter;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tencoding.todo.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class JwtRequestFilter implements Filter {
	
	@Autowired
	private JwtUtil jwtUtil;
	

	
	
	// doFilter -> 약속 : 요청이 오면 반드시 doFilter가 호출 된다. 
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		log.info("JWT Filter 동작 확인 - 1");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String authHader = httpServletRequest.getHeader("Authorization");
		
		if(authHader != null) {
			// 토큰 존재 여부 확인 
			if(!authHader.startsWith("Bearer ")) {
				sendError(response); 
				return; 
			}
			
			// 토큰 존재 여부 확인 
			if(!authHader.startsWith("Bearer ")) {
				sendError(response);
				return;
			}
			

			String jwtToken = authHader.substring(7);
			// 토큰 유효성 확인 
			if(!jwtUtil.validateToken(jwtToken)) {
				sendError(response); 
				return; 
			}
			
			
		}
		
		
		
		log.info("JWT Filter 동작 확인 - 2");
		chain.doFilter(request, response);
		
	}




	private void sendError(ServletResponse response) throws IOException {
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
	}

}
