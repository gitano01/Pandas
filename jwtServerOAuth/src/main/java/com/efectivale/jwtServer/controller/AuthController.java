package com.efectivale.jwtServer.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.efectivale.jwtServer.utils.Utils;
import com.efectivale.jwtServer.validator.AuthValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RequestMapping(path = ConstantesJwt.Oauth.VERSION)

@RestController
@Api(tags="Auth Token Api")
public class AuthController {	
	
	@Autowired
	private Utils util;
	@Autowired
	private AuthService service;
	@Autowired
	private AuthValidator validator;
	
	
	private static final Logger LOG = Logger.getLogger(AuthController.class.getName());
	
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
			, @RequestParam String api,	@RequestParam(ConstantesJwt.Params.GRANT_TYPE) String grantType, HttpServletRequest request) throws Exception{
			validator.validate(username,password,grantType);
			
			try {
				LOG.info(ConstantesJwt.Oauth.log.PROCESS_BEGIN + AuthService.class.getName()+ " con parametros: usuario"+ username + " api: " + api + ", ipConsumidor:"  +  util.getClientIpAddress(request));
				ResponseEntity<ApiJwtResponse> response = service.login(username,
						password, api,request);
				LOG.info(ConstantesJwt.Oauth.log.PROCESS_END+AuthController.class.getName()+ " con parametros: usuario"+  username + " api: " + api   + ", ipConsumidor:"  +  util.getClientIpAddress(request));
			return  response;
			}catch(Exception e) {				
				LOG.log(Level.SEVERE, ConstantesJwt.Oauth.log.PROCESS_INTERRUPTOR +" en la clase"+ AuthController.class.getName()+ " por error: "+  e.getMessage(), e.getMessage());
				throw new Exception(e.getMessage());
			}
	}
}
