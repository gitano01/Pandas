package com.victor.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.victor.dto.ApiResponse;
import com.victor.dto.FailedResponse;
import com.victor.dto.SuccessResponse;
import com.victor.security.JwtIO;

import io.fusionauth.jwt.InvalidJWTSignatureException;
import io.fusionauth.jwt.JWTExpiredException;

@Component
public class Validator {

	@Autowired
	private JwtIO jwtIO;
	
	public ResponseEntity<ApiResponse> validate(HttpServletRequest request){
		
		String cause = null;
		ApiResponse req = null;
		ResponseEntity<ApiResponse> response = null;
		
		if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()){
			try {
				boolean validate = false;
				String token = request.getHeader("Authorization").replace("Bearer ", "");
				  validate = !jwtIO.validateToken(token);
				req = new SuccessResponse(200,"Operación Exitosa", String.valueOf(validate));
				response = new ResponseEntity<ApiResponse>(req, HttpStatus.OK);
			} catch (InvalidJWTSignatureException se) {
				cause = "No estás autorizado para consumir este recurso";
				req = new FailedResponse(401,"Operación fallida", cause);
				response = new ResponseEntity<ApiResponse>(req, HttpStatus.UNAUTHORIZED);				
			} catch (JWTExpiredException et) {
				cause = "Token expirado";
				req = new FailedResponse(401,"Operación fallida", cause);
				response = new ResponseEntity<ApiResponse>(req, HttpStatus.UNAUTHORIZED);				
			} catch (Exception e) {
				Exception ex = new Exception();
				ex.initCause(e);
				cause = ex.getCause().toString();
				req = new FailedResponse(500,"Operación fallida", cause);
				response = new ResponseEntity<ApiResponse>(req, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
		} else {
			req = new FailedResponse(400,"Operación fallida", "El token es requerido");
			response = new ResponseEntity<ApiResponse>(req, HttpStatus.BAD_REQUEST);	
		}
		
		return response;
	}
	
}
