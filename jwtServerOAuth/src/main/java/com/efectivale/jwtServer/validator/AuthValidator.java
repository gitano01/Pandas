package com.efectivale.jwtServer.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.efectivale.jwtServer.exceptions.APIJwtBadRequest;
import com.efectivale.jwtServer.exceptions.APIJwtUnauthorized;

@Component
public class AuthValidator {

	private static final String CLIENT_CREDENTIALS = "client_credentials";
	
	public void validate(MultiValueMap<String,String> paramMap, String grantType) throws APIJwtUnauthorized {
		
		if(grantType.isEmpty()|| !grantType.equals(CLIENT_CREDENTIALS)){
			messageUnauthorized("El tipo de credenciales no es válido");
		}
		if(Objects.isNull(paramMap) || paramMap.getFirst("username").isEmpty() || paramMap.getFirst("password").isEmpty()) {
			messageUnauthorized("usernamey/o password son incorrectos");
		}
		
	}
	
	public void validaJwt(String jwt) throws  APIJwtBadRequest{
		if(jwt.isEmpty() || jwt.equals("")) {
			messageBadRequest("El parametro JWT no debe estar vacío o nulo");
		}
	}
	
	private void messageBadRequest(String message) throws APIJwtBadRequest{
		
		throw new APIJwtBadRequest(message);
		
	}
	private void messageUnauthorized(String message) throws APIJwtUnauthorized{
		
		throw new APIJwtUnauthorized(message);
		
	}
	
}
