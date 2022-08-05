package com.efectivale.jwtServer.dto;

public class ErrorResponse  extends ApiJwtResponse {

	
	private String detalles;

	public ErrorResponse(int codigo, String mensaje,String detalles) {
		super(codigo,mensaje);
		this.detalles = detalles;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	
	
}
