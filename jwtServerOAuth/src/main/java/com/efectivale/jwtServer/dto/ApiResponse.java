package com.efectivale.jwtServer.dto;

public class ApiResponse extends ApiJwtResponse {

	private Object response;

	public ApiResponse(int status, String mensaje,Object response) {
		super(status,mensaje);
		this.response = response;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}
	
	
	
	
	
}
