package com.efectivale.jwtServer.exceptions;

import com.efectivale.jwtServer.dto.JwtResponse;

import lombok.Data;

@Data
public class ApiResponse extends ApiJwtResponse {

	private JwtResponse jwtResponse;

	public ApiResponse(int status, String mensaje,JwtResponse jwtResponse) {
		super(status,mensaje);
		this.jwtResponse = jwtResponse;
	}
	
	
	
}
