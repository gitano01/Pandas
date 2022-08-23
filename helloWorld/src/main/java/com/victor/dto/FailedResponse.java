package com.victor.dto;

public class FailedResponse extends ApiResponse {

	public FailedResponse(int codigo, String mensaje, String detalles) {
		super(codigo, mensaje);
		this.detalles = detalles;
	}

	private String detalles;

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
}
