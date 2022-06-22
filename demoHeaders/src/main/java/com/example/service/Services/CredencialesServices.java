package com.example.service.Services;


import org.springframework.stereotype.Service;

import com.example.models.CredencialesGeneradas;

import com.example.models.UsuarioRequest;
import com.example.service.Implements.CredencialesImpl;
import com.example.utils.FuncionesGenericas;

@Service
public class CredencialesServices implements CredencialesImpl {
	
	
	FuncionesGenericas func = new FuncionesGenericas();
	
	public CredencialesGeneradas generaCredencial(UsuarioRequest usuario) {
		 
		
		char[] charsUsuario = usuario.getNombre().toCharArray();
		char[] charsPassword = usuario.getPassword().toCharArray();
		
		
		String consumerKey = func.generador(charsUsuario);
		String consumerSecret = func.generador(charsPassword);
		
		CredencialesGeneradas  credeGen = new CredencialesGeneradas(usuario.getNombre(),consumerKey,consumerSecret);
				
		return credeGen;
		
		
	}
	
	public CredencialesResponse asignarCredenciales(UsuarioRequest usuario) {
		
		
	} 
	
	
	
	

}
