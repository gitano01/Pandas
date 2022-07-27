package com.efectivale.jwtServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.dto.ApiResponse;
import com.efectivale.jwtServer.dto.ErrorResponse;
import com.efectivale.jwtServer.dto.JwtResponse;
import com.efectivale.jwtServer.exceptions.APIJwtUnauthorized;
import com.efectivale.jwtServer.service.AuthService;
import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.efectivale.jwtServer.validator.AuthValidator;

@RestController
@RequestMapping(path = "v1")
public class AuthController {	
	@Autowired
	private AuthService service;
	@Autowired
	private AuthValidator validator;

	@PostMapping(path =  ConstantesJwt.Oauth.GENERATION_TOKEN , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ApiJwtResponse> login(@RequestBody MultiValueMap<String, String> paramMap,
			@RequestParam(ConstantesJwt.Params.GRANT_TYPE) String grantType) throws APIJwtUnauthorized,Exception{
			validator.validate(paramMap, grantType);
			JwtResponse jwt = null;
			ResponseEntity<ApiJwtResponse> apiResponse = null;
			ApiJwtResponse response = null;
			try {
				jwt = service.login(paramMap.getFirst(ConstantesJwt.Params.USERNAME), paramMap.getFirst(ConstantesJwt.Params.PASSWORD), paramMap.getFirst(ConstantesJwt.Params.SERVICE));
				
				response = new ApiResponse(200,"Operación Exitosa",jwt);
				
				apiResponse = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.OK);
				
			}catch(Exception e) {
				
				String valueError = e.getMessage();
				
						if(valueError == "No existen registros") {
							response = new ErrorResponse(404,"Operación fallida",e.getMessage());					
							apiResponse = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.NOT_FOUND);		
						}else {
						response = new ErrorResponse(500,"Operación fallida",e.getMessage());					
						apiResponse = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.INTERNAL_SERVER_ERROR);
						}
					
				
				
			}	
			return apiResponse;			
	}
	
	
	
}
