package com.efectivale.jwtServer.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.efectivale.jwtServer.dto.ApiData;
import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.dto.ApiResponse;
import com.efectivale.jwtServer.dto.DataUser;
import com.efectivale.jwtServer.dto.ErrorResponse;
import com.efectivale.jwtServer.dto.JwtResponse;
import com.efectivale.jwtServer.security.JwtIO;
import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.efectivale.jwtServer.utils.DateUtils;
import com.efectivale.jwtServer.utils.ConexionBD;


@Service
public class AuthService {

	
	
	@Autowired
	private JwtIO jwtIO;
	@Autowired
	private DateUtils dateUtils;
	@Value("${efv.jwt.token.expires-in}")
	private int EXPIRES_IN;	

	ConexionBD conexion = new ConexionBD();
	public ResponseEntity<ApiJwtResponse> login(String username, String password, String servicio) {

		// Ir a la base datos
		DataUser dUser = null;		
		JwtResponse jwt = null;
		ApiJwtResponse response= null;
		ResponseEntity<ApiJwtResponse> apiResponse = null;
	
		try {			
			dUser = conexion.datosCredencial(username, password, servicio);
			ApiData api = new ApiData();;
			api.setApi(dUser.getRs_api());
			api.setCredencialActiva(dUser.getRs_credencial_activa());			
			jwt = JwtResponse.builder().tokenType("bearer")
					.accessToken(jwtIO.generateToken(api, dUser.getRs_consumersecret()))
					.issuedAt(dateUtils.getDateMillis() + "")
					.clienteId(username).expiresIn(EXPIRES_IN).build();			
			response = new ApiResponse(ConstantesJwt.Codes.OK,ConstantesJwt.ApiResponses.EXIT,jwt);
			apiResponse  = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.OK);
		}catch(Exception e) {
			
			String valueError = e.getMessage();
			int op = 0;
			//Errors USER
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.USERNAMEPASSWORD_ERROR)) {op = 1;}
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.USERNAMEACTIVE_ERROR)) {op = 2;}
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.USERNAME_NOCREDENTIALS )) {op = 3;}
			//Error credenciales
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.CREDENTIAL_USER_ACTIVE_ERROR )) {op = 4;}
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.CREDENTIAL_API_ACTIVE_ERROR)) {op = 5;	}
			//Errors API
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.API_NOTFOUND)) {	op = 6;}
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.API_ACTIVE_ERROR )) {op = 7;}
			if(valueError.contains(ConstantesJwt.Oauth.errorsDB.API_NOCREDENTIALS )) {op = 8;}	
			
			switch (op) {
			case 1:
				response = new ErrorResponse(ConstantesJwt.Codes.BAD_REQUEST,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.USERNAMEPASSWORD_ERROR );
				apiResponse  =  new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.BAD_REQUEST);
				break;
			case 2: 
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.USERNAMEACTIVE_ERROR );
				apiResponse  =  new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			case 3: 
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.USERNAME_NOCREDENTIALS  );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			case 4:
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.CREDENTIAL_USER_ACTIVE_ERROR );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			case 5:
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.CREDENTIAL_API_ACTIVE_ERROR );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			case 6: 
				response = new ErrorResponse(ConstantesJwt.Codes.NOT_FOUND,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.API_NOTFOUND);
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.NOT_FOUND);
				break;
			case 7: 
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.API_ACTIVE_ERROR );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			case 8:
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, ConstantesJwt.Oauth.errorsDB.API_NOCREDENTIALS );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				break;
			default:
				response = new ErrorResponse(ConstantesJwt.Codes.INTERNAL_ERROR,ConstantesJwt.ApiResponses.FAILURE, e.getMessage());
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.INTERNAL_SERVER_ERROR);
				break;
			}
		}
		return apiResponse;		
	}
}
