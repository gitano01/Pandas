package com.efectivale.jwtServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.efectivale.jwtServer.dto.JwtResponse;
import com.efectivale.jwtServer.security.JwtIO;
import com.efectivale.jwtServer.utils.DateUtils;

@Service
public class AuthService {

	@Autowired 
	private JwtIO  jwtIO;
	@Autowired
	private DateUtils dateUtils;
	@Value("${efv.jwt.token.expires-in}")
	private int EXPIRES_IN;
	
	public JwtResponse login(String clientId,String clientSecret) {
		
		
		JwtResponse jwt = JwtResponse.builder()
				.tokenType("bearer")
				.accessToken(jwtIO.generateToken("Hola Mundo desde AuthService"))
				.issuedAt(dateUtils.getDateMillis() + "")
				.clienteId(clientId)
				.expiresIn(EXPIRES_IN)
				.build();
		
		return jwt;
	}
	
}
