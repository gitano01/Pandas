package com.jwt.jwtServices.dao.implement;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.jwt.jwtServices.model.ApiResponse;

public interface JwtImplement {

	public ResponseEntity<ApiResponse> getJwToken(Map<String,String> map);
	
}
