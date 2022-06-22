package com.example.models;

public class SolicitudParams {

	private String nombre;
	private String apellidos;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	
	
	public String toString(String nombre, String apellidos) {
		return "nombre: "+ nombre + "Apellidos: " + apellidos; 
	}
	
	
}
