package com.example.models;

public class UsuarioRequest {
	
	public String nombre;
	public String password;
	
	
	public UsuarioRequest(String nombre, String password) {
		super();
		this.nombre = nombre;
		this.password = password;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String MostrarDatos(String nombre, String password) {
		return  "nombre: "+ nombre + " password: "+  password;
	}
	

}
