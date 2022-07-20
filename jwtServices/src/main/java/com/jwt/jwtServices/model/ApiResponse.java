package com.jwt.jwtServices.model;

public class ApiResponse {

	private String status;
	private String mensaje;
	private Object response;
	
	
	
	public ApiResponse(String status, String mensaje, Object response) {
		super();
		this.status = status;
		this.mensaje = mensaje;
		this.response = response;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", mensaje=" + mensaje + ", response=" + response + "]";
	}
	
	
}
