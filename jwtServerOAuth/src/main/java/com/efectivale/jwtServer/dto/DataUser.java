package com.efectivale.jwtServer.dto;

import lombok.Data;

@Data
public class DataUser {

	private String rs_username;
	private String rs_password;               
	private int rs_credencialid;
	private String rs_consumerkey;
	private String rs_consumersecret;
	private String rs_servicio;
	private String rs_url;
	
	
}
