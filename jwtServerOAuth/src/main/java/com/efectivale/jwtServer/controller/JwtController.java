package com.efectivale.jwtServer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class JwtController {

	@GetMapping("/version")
	public ResponseEntity<String> version(){		
		return new ResponseEntity<String>("Version 1.0", HttpStatus.OK);
	}
	
@GetMapping ("/status")
	public  ResponseEntity<Object> status() {		
	    Map<String,Object> result = new HashMap<String,Object>();		
	    result.put("codigo", 200);
	    result.put("mensaje", "Operacion exitosa");
	    result.put("response", "Hola soy el servicio de token y me encuentro en operación");		
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
}
