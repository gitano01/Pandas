package com.efectivale.jwtServer.dto;

import lombok.Data;

@Data
public class ErrorResponse  extends ApiJwtResponse {

	
	private String detalles;

	public ErrorResponse(int status, String mensaje,String detalles) {
		super(status,mensaje);
		this.detalles = detalles;
	}
}
