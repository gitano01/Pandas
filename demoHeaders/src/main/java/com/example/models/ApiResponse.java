package com.example.models;

public class ApiResponse {
	
	protected int codigo;
	protected String mensaje;
	
	public ApiResponse(int codigo,String mensaje) {
		this.codigo = codigo;
		this.mensaje = mensaje;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
