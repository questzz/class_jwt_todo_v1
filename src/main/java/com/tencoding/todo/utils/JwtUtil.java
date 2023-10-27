package com.tencoding.todo.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component // Ioc 대상 된다. 
public class JwtUtil {
	
	// 시크릿 키를 생성 - xxkdk --> 동적 생성 ()
	private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	private final long EXP_TIME = 86400000L; // 유효 시간 1일  
		
	// 토큰 생성하는 메서드를 만들어야 한다.
	public String generateToken(String userEmail, Integer userId) {
		return Jwts.builder()
				.claim("userEmail", userEmail)
				.claim("userId", userId)
				.setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
				.signWith(key)
				.compact();		
	}
	
	// 토큰이 유효한지 검증 메서드를 만들어야 한다.
	public Boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// 유저에 정보를 뽑고 싶은 메서드를 만들어야 한다. - email 
	public String getUserEmailFromToken(String token) {
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws("userEmail");
		
		return claimsJws.getBody().get("userEmail", String.class);
	}
	
	
	// 유저 정보에서 userId를 뽑는 메서드를 만들어 볼 예정 
	public String getUserIdFromToken(String token) {
		Jws<Claims> claimsJws = Jwts.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws("userId");
		
		return claimsJws.getBody().get("userId", String.class);
	}
	
}
