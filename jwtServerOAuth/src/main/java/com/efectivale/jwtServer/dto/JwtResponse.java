package com.efectivale.jwtServer.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse{
	
	
	private String tokenType;		
	private String accessToken;			
	private int expiresIn;		
	private String issuedAt;	
	private String clienteId;

}
