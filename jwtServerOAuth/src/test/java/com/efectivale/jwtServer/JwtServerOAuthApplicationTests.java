package com.efectivale.jwtServer;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest


class JwtServerOAuthApplicationTests {

	@Test
	public ResponseEntity<Object> contextLoads() {
		Map<String,Object> result = new HashMap<String,Object>();		
	    result.put("codigo", 200);
	    result.put("mensaje", "Operacion exitosa");
	    result.put("response", "Hola soy el servicio de token y me encuentro en operación");		
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
	
	
	

}
