package com.example.models;

public class Response extends ApiResponse{
	
	protected Object response;

	public Response(int codigo, String mensaje, Object response) {
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
