package com.example.service.Services;


import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.models.CredencialesGeneradas;
import com.example.models.CredencialesResponse;
import com.example.models.UsuarioRequest;
import com.example.service.Implements.CredencialesImpl;
import com.example.utils.FuncionesGenericas;

@Service
public class CredencialesServices implements CredencialesImpl {
	
	
	FuncionesGenericas func = new FuncionesGenericas();
	
	public String generaCredencial(UsuarioRequest usuario) {
		 
		String inserta = null;
		char[] charsUsuario = usuario.getNombre().toCharArray();
		char[] charsPassword = usuario.getPassword().toCharArray();
		
		usuario.setPassword(Base64.getEncoder().encodeToString(usuario.getPassword().getBytes()));
				
		String consumerKey = func.generador(charsUsuario);
		String consumerSecret = func.generador(charsPassword);
		
		CredencialesGeneradas  credeGen = new CredencialesGeneradas(usuario.getNombre(),usuario.getPassword(),consumerKey,consumerSecret);
				
		inserta =  func.asignarCredenciales(credeGen);
		
		return inserta;
		
		
	}
	
	public List<CredencialesResponse> obtenerCredenciales(){
		
		return func.obtenerCredenciales();
		
	}
	
	
	
	
	
	

}
