package com.efectivale.jwtServer.dto;

import lombok.Data;

@Data
public class ApiResponse extends ApiJwtResponse {

	private Object response;

	public ApiResponse(int status, String mensaje,Object response) {
		super(status,mensaje);
		this.response = response;
	}
	
	
	
}
