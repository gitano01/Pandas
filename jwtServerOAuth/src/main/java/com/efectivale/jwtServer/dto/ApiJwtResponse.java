package com.efectivale.jwtServer.dto;

import lombok.Data;

@Data
public abstract class ApiJwtResponse {
	private int  codigo;
	private String mensaje;
	public ApiJwtResponse(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}	
	}
