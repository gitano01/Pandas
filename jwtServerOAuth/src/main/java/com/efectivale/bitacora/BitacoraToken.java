package com.efectivale.bitacora;

import java.sql.Date;

import lombok.Data;

@Data
public class BitacoraToken {
	private String usuario;
	private String api;
	private Date bitacoraFecha;
	private String token;
	private String ipConsumidor;
	private Long idUsuario;
	
	public BitacoraToken(String usuario, String api, Date bitacoraFecha, String token, String ipConsumidor,
			Long idUsuario) {
		super();
		this.usuario = usuario;
		this.api = api;
		this.bitacoraFecha = bitacoraFecha;
		this.token = token;
		this.ipConsumidor = ipConsumidor;
		this.idUsuario = idUsuario;
	}
	public BitacoraToken() {}
	
}