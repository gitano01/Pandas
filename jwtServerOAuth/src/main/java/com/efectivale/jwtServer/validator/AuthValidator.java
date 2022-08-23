package com.efectivale.jwtServer.validator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.dto.ErrorResponse;
import com.efectivale.jwtServer.utils.ConstantesJwt;

@Component
public class AuthValidator {

	public ResponseEntity<ApiJwtResponse> validate( HttpServletRequest request, String grantType){
		
		ApiJwtResponse jwtResponse = null;
		ResponseEntity<ApiJwtResponse> apiResponse = null;
		
		//checar si las headers de Basic no estan nulos
		
		if(request.getHeader("Authorization").isEmpty() || request.getHeader("Authorization") == null) {
			jwtResponse = new ErrorResponse(ConstantesJwt.Codes.BAD_REQUEST,ConstantesJwt.ApiResponses.FAILURE, 
					ConstantesJwt.ParamsError.USER_PASS_ERROR);
			apiResponse = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)jwtResponse,HttpStatus.BAD_REQUEST);
		}
		
		
		if(grantType.isEmpty() || !grantType.equals(ConstantesJwt.Params.CLIENT_CREDENTIALS)){
			
			jwtResponse = new ErrorResponse(ConstantesJwt.Codes.BAD_REQUEST,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.ParamsError.GRANT_TYPE_ERROR );
			apiResponse = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)jwtResponse,HttpStatus.BAD_REQUEST);
	   }
				
		return apiResponse;
		
	}
}
