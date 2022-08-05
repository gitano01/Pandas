package com.efectivale.jwtServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.swaggerdto.SwaggerDocResposes.*;
import com.efectivale.jwtServer.service.AuthService;
import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.efectivale.jwtServer.validator.AuthValidator;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RequestMapping(path = ConstantesJwt.Oauth.VERSION)

@RestController

public class AuthController {	
	@Autowired
	private AuthService service;
	@Autowired
	private AuthValidator validator;
	
	@ApiResponses(value= {
			@ApiResponse(code= ConstantesJwt.Codes.OK, message= ConstantesJwt.Swagger.OK, response=ApiJwtMockResponse.class),
			@ApiResponse(code = ConstantesJwt.Codes.BAD_REQUEST, message = ConstantesJwt.Swagger.BAD_REQUEST,response=Error400.class),
			@ApiResponse(code = ConstantesJwt.Codes.UNAUTHORIZED, message = ConstantesJwt.Swagger.UNAUTHORIZED,response=Error401.class),
			@ApiResponse(code = ConstantesJwt.Codes.NOT_FOUND, message = ConstantesJwt.Swagger.NOT_FOUND,response=Error404.class),
			@ApiResponse(code = ConstantesJwt.Codes.INTERNAL_ERROR, message = ConstantesJwt.Swagger.INTERNAL_ERROR,response=Error500.class) 
	})
	@ApiOperation(value= ConstantesJwt.Swagger.CONTROLLER_DESCRIPTION, response = ApiJwtResponse.class)
	@PostMapping(path =  ConstantesJwt.Oauth.GENERATION_TOKEN , produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ApiJwtResponse> login(@RequestHeader String username, @RequestHeader String password
			, @RequestParam String api,	@RequestParam String grantType) throws Exception{
			validator.validate(username,password,grantType);
			try {
			return  service.login(username,
					password, api);
			}catch(Exception e) {
				
				throw new Exception(e.getMessage());
			}
	}
}
