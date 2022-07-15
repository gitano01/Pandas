package com.example.models;

public class CredencialesResponse {
	
	private Long credencialid;
	private String credencialusuario;
	private String credencialconsumerkey;
	private String credencialconsumersecret;
	private String credencialfechacreacion;
	private String credencialfechamodificacion;
	private Boolean credenciaactiva;
	public Long getCredencialid() {
		return credencialid;
	}
	public void setCredencialid(Long credencialid) {
		this.credencialid = credencialid;
	}
	public String getCredencialusuario() {
		return credencialusuario;
	}
	public void setCredencialusuario(String credencialusuario) {
		this.credencialusuario = credencialusuario;
	}
	public String getCredencialconsumerkey() {
		return credencialconsumerkey;
	}
	public void setCredencialconsumerkey(String credencialconsumerkey) {
		this.credencialconsumerkey = credencialconsumerkey;
	}
	public String getCredencialconsumersecret() {
		return credencialconsumersecret;
	}
	public void setCredencialconsumersecret(String credencialconsumersecret) {
		this.credencialconsumersecret = credencialconsumersecret;
	}
	public String getCredencialfechacreacion() {
		return credencialfechacreacion;
	}
	public void setCredencialfechacreacion(String credencialfechacreacion) {
		this.credencialfechacreacion = credencialfechacreacion;
	}
	public String getCredencialfechamodificacion() {
		return credencialfechamodificacion;
	}
	public void setCredencialfechamodificacion(String credencialfechamodificacion) {
		this.credencialfechamodificacion = credencialfechamodificacion;
	}
	public Boolean getCredenciaactiva() {
		return credenciaactiva;
	}
	public void setCredenciaactiva(Boolean credenciaactiva) {
		this.credenciaactiva = credenciaactiva;
	}
	
	@Override
	public String toString() {
		return "CredencialesResponse [credencialid=" + credencialid + ", credencialusuario=" + credencialusuario
				+ ", credencialconsumerkey=" + credencialconsumerkey + ", credencialconsumersecret="
				+ credencialconsumersecret + ", credencialfechacreacion=" + credencialfechacreacion
				+ ", credencialfechamodificacion=" + credencialfechamodificacion + ", credenciaactiva="
				+ credenciaactiva + "]";
	}
	

	

}
