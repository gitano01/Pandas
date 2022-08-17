package com.victor.dto;

public class SuccessResponse extends ApiResponse {

	private String response;

	public SuccessResponse(int codigo, String mensaje, String response) {
		super(codigo, mensaje);
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}	
}
