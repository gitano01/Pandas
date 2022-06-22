package com.example.service.Implements;

import java.util.List;

import com.example.models.CredencialesResponse;
import com.example.models.UsuarioRequest;

public interface CredencialesImpl {

	public String generaCredencial(UsuarioRequest usuario);
	public List<CredencialesResponse> obtenerCredenciales();
	
	
}
