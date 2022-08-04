package com.efectivale.jwtServer.dto;

public class ErrorResponse  extends ApiJwtResponse {

	
	private String detalles;

	public ErrorResponse(int status, String mensaje,String detalles) {
		super(status,mensaje);
		this.detalles = detalles;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
	
	
}
