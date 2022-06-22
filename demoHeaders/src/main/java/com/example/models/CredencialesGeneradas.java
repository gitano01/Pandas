package com.example.models;

public class CredencialesGeneradas {

	private String usuario;
	private String contrasenia;
	private String consumerKey;
	private String consumerSecret;
	
	
	
	public CredencialesGeneradas(String usuario, String contrasenia,String consumerKey, String consumerSecret) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	public String getConsumerKey() {
		return consumerKey;
	}
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	public String getConsumerSecret() {
		return consumerSecret;
	}
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	
}
