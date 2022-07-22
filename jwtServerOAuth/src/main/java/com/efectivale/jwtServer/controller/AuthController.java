package com.efectivale.jwtServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.efectivale.jwtServer.exceptions.APIJwtUnauthorized;
import com.efectivale.jwtServer.service.AuthService;
import com.efectivale.jwtServer.validator.AuthValidator;

@RestController
@RequestMapping(path = "v1")
public class AuthController {

	@Autowired
	private AuthService service;
	@Autowired
	private AuthValidator validator;

	@PostMapping(path = "oauth/client_credential/accesstoken", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Object> login(@RequestBody MultiValueMap<String, String> paramMap,
			@RequestParam("grant_type") String grantType) throws APIJwtUnauthorized{
			validator.validate(paramMap, grantType);
			return ResponseEntity.ok(service.login(paramMap.getFirst("client_id"), paramMap.getFirst("client_secret")));
	}
}
