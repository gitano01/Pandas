package com.efectivale.jwtServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.dto.UserLogin;

import com.efectivale.jwtServer.service.AuthService;
import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.efectivale.jwtServer.validator.AuthValidator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import mx.com.efectivale.efectivacontigo.utils.ApiUtils.Response;

@RequestMapping(path = ConstantesJwt.Oauth.VERSION)
@ApiResponses(value= {
		@ApiResponse(code=ConstantesJwt.Codes.OK, message= ConstantesJwt.ApiResponses.EXIT),
		@ApiResponse(code = ConstantesJwt.Codes.BAD_REQUEST, message = ConstantesJwt.ApiResponses.FAILURE),
		@ApiResponse(code = ConstantesJwt.Codes.UNAUTHORIZED, message = ConstantesJwt.ApiResponses.FAILURE),
		@ApiResponse(code = ConstantesJwt.Codes.NOT_FOUND, message = ConstantesJwt.ApiResponses.FAILURE),
		@ApiResponse(code = ConstantesJwt.Codes.INTERNAL_ERROR, message = ConstantesJwt.ApiResponses..FAILURE) 
})
@RestController

public class AuthController {	
	@Autowired
	private AuthService service;
	@Autowired
	private AuthValidator validator;
	
	 
	@ApiOperation(value= ConstantesJwt.Swagger.CONTROLLER_DESCRIPTION, response = ApiJwtResponse.class)
	@PostMapping(path =  ConstantesJwt.Oauth.GENERATION_TOKEN , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ApiJwtResponse> login(@RequestBody UserLogin userLogin, @RequestParam String api,
			@RequestParam String grantType) throws Exception{
			validator.validate(userLogin,grantType);
			try {
			return  service.login(userLogin.getUsername(),
					userLogin.getPassword(), api);
			}catch(Exception e) {
				
				throw new Exception(e.getMessage());
			}
	}
}
