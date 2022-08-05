package com.efectivale.jwtServer.dto;

public class ApiResponse extends ApiJwtResponse {

	private JwtResponse responseToken;

	public ApiResponse(int codigo, String mensaje,JwtResponse jwtTokenresponse) {
		super(codigo,mensaje);
		this.responseToken = jwtTokenresponse;
	}

	public JwtResponse getResponse() {
		return responseToken;
	}

	public void setResponse(JwtResponse jwtTokenresponse) {
		this.responseToken = jwtTokenresponse;
	}
	
	
	
	
	
}
