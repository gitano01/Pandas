package com.victor.dto;

public class SuccessResponse extends ApiResponse {

	private Object response;

	public SuccessResponse(int codigo, String mensaje, Object response) {
		super(codigo, mensaje);
		this.response = response;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}	
}
