package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.models.SolicitudParams;


@RestController
@RequestMapping("/gestionHeaders")
public class DemoHeadersController {

	@PostMapping("/test")
	public ResponseEntity<?> pruebaHeaders(@RequestBody SolicitudParams solicitud, @RequestHeader(name="x-usuario", required = true) String usuario) throws Exception {
		
		
		if(usuario.equals("leonado2310")) {
			
			System.out.println(solicitud.toString(solicitud.getNombre(), solicitud.getApellidos()));
			return new ResponseEntity<String>("Saludo header: "+ usuario, HttpStatus.OK);
			
		}else {
			return new ResponseEntity<String>("Error unauthorized", HttpStatus.UNAUTHORIZED);
		}
		
		
		
	}
	
	
	
}
