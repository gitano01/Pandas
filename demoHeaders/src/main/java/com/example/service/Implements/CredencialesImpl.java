package com.example.service.Implements;

import com.example.models.CredencialesGeneradas;
import com.example.models.CredencialesResponse;
import com.example.models.UsuarioRequest;

public interface CredencialesImpl {

	public CredencialesGeneradas generaCredencial(UsuarioRequest usuario);
	public CredencialesResponse asignarCredencial(UsuarioRequest usuario);
	
}
