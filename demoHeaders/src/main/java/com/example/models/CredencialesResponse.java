package com.example.models;

public class CredencialesResponse {
	
	private Long credencialid;
	private String credencialUsuario;
	private String credencialConsumerKey;
	private String credencialConsumerSecret;
	private String credencialFechaCreacion;
	private String credencialFechaModificacion;
	private Boolean credenciaActiva;
	

	public Long getCredencialid() {
		return credencialid;
	}
	public void setCredencialid(Long credencialid) {
		this.credencialid = credencialid;
	}
	public String getCredencialUsuario() {
		return credencialUsuario;
	}
	public void setCredencialUsuario(String credencialUsuario) {
		this.credencialUsuario = credencialUsuario;
	}
	public String getCredencialConsumerKey() {
		return credencialConsumerKey;
	}
	public void setCredencialConsumerKey(String credencialConsumerKey) {
		this.credencialConsumerKey = credencialConsumerKey;
	}
	public String getCredencialConsumerSecret() {
		return credencialConsumerSecret;
	}
	public void setCredencialConsumerSecret(String credencialConsumerSecret) {
		this.credencialConsumerSecret = credencialConsumerSecret;
	}
	public String getCredencialFechaCreacion() {
		return credencialFechaCreacion;
	}
	public void setCredencialFechaCreacion(String credencialFechaCreacion) {
		this.credencialFechaCreacion = credencialFechaCreacion;
	}
	public String getCredencialFechaModificacion() {
		return credencialFechaModificacion;
	}
	public void setCredencialFechaModificacion(String credencialFechaModificacion) {
		this.credencialFechaModificacion = credencialFechaModificacion;
	}
	public Boolean getCredenciaActiva() {
		return credenciaActiva;
	}
	public void setCredenciaActiva(Boolean credenciaActiva) {
		this.credenciaActiva = credenciaActiva;
	}	

}
