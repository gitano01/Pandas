package com.efectivale.jwtServer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import com.efectivale.jwtServer.controller.JwtController;

@SpringBootTest


class JwtServerOAuthApplicationTests {

	@Test
	public ResponseEntity<Object> contextLoads() {
			JwtController jwtController = new JwtController();
			ResponseEntity<Object> result = jwtController.status();
			return result;
	}
	
	
	
	

}
