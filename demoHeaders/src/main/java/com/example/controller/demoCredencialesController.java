package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.ApiResponse;
import com.example.models.CredencialesGeneradas;
import com.example.models.ErrorResponse;
import com.example.models.Response;
import com.example.models.UsuarioRequest;
import com.example.service.Services.CredencialesServices;

@RestController
@RequestMapping({"/gestionCredenciales"})
public class demoCredencialesController {
	
	@Autowired
	CredencialesServices servCrede;
	
	ApiResponse apiResponse;
	
	@PostMapping("/generar")
	public ResponseEntity<ApiResponse> generaCredentials(@RequestBody UsuarioRequest usuario) throws Exception{

		CredencialesGeneradas credenciales = null;
		try {
			
			credenciales = servCrede.generaCredencial(usuario);
			
			
			if(credenciales != null) {
			apiResponse = new Response (200,"Operacion Exitosa", credenciales);
			
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
			}else{
				apiResponse = new ErrorResponse(400,"Operacion Fallida","http://efevserv.com/BadRequest", "problemas al generar credenciales");
				
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception ex) {
			
			apiResponse = new ErrorResponse(500,"Operacion Fallida","http://efevserv.com/InternalError", ex.getMessage());
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
		
	}
	
	
	

}
