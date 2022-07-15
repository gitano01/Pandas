package com.example.models;

public class CredencialesGeneradas {

	private String usuario;
	private String consumerKey;
	private String consumerSecret;
	
	
	
	public CredencialesGeneradas(String usuario, String consumerKey, String consumerSecret) {
		super();
		this.usuario = usuario;
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
