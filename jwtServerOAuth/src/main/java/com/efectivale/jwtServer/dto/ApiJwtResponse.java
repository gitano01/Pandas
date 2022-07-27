package com.efectivale.jwtServer.dto;

import lombok.Data;

@Data
public class ApiJwtResponse {
	private int  status;
	private String mensaje;
	public ApiJwtResponse(int status, String mensaje) {
		super();
		this.status = status;
		this.mensaje = mensaje;
	}	
	}
