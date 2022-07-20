package com.jwt.jwtServices.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.jwt.jwtServices.dao.service.JwtService;
import com.jwt.jwtServices.model.ApiResponse;

@RestController
@RequestMapping("/")
public class jwtController {

	@Autowired
	JwtService jwtService;
	
	@PostMapping("/oauth/token")
	public @ResponseBody ResponseEntity<ApiResponse> obtenerToken(@RequestParam String username, @RequestParam String password,
			@RequestParam String grant_type, @RequestParam String client_id,@RequestParam String client_secret) throws Exception {

		Map<String,String> map = new HashMap<String,String>();
		map.put("client_id", client_id);
		map.put("client_secret", client_secret);
		map.put("grant_type", grant_type);
		map.put("username", username);
		map.put("password", password);
		
		ResponseEntity<ApiResponse> res = jwtService.getJwToken(map);
		return res;

	}

	@GetMapping("/helloWorld")
	public String saludo() {
		return "Entré";
	}

}
