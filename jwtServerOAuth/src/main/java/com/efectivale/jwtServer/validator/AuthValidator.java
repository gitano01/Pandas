package com.efectivale.jwtServer.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.efectivale.jwtServer.exceptions.APIJwtUnauthorized;

@Component
public class AuthValidator {

	private static final String CLIENT_CREDENTIALS = "client_credentials";
	
	public void validate(MultiValueMap<String,String> paramMap, String grantType) throws APIJwtUnauthorized {
		
		if(grantType.isEmpty()|| !grantType.equals(CLIENT_CREDENTIALS)){
			message("El tipo de credenciales no es válido");
		}
		if(Objects.isNull(paramMap) || paramMap.getFirst("username").isEmpty() || paramMap.getFirst("password").isEmpty()) {
			message("usernamey/o password son incorrectos");
		}
		
	}
	
	private void message(String message) throws APIJwtUnauthorized{
		
		throw new APIJwtUnauthorized(message);
		
	}
	
}
