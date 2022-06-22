package com.example.models;

public class ErrorResponse extends ApiResponse {

	private String info;
	private String detalles;
	
	public ErrorResponse(int codigo, String mensaje, String info, String detalles) {
		super(codigo, mensaje);
		this.info = info;
		this.detalles = detalles;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	
	
}
