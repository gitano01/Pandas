package com.efectivale.jwtServer.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.efectivale.jwtServer.dto.ApiJwtResponse;
import com.efectivale.jwtServer.dto.ApiResponse;
import com.efectivale.jwtServer.dto.ErrorResponse;
import com.efectivale.jwtServer.dto.JwtResponse;
import com.efectivale.jwtServer.security.JwtIO;
import com.efectivale.jwtServer.utils.ConexionBD;
import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.efectivale.jwtServer.utils.DateUtils;
import com.efectivale.jwtServer.utils.TypesEncription;
import com.efectivale.jwtServer.utils.Utils;
import com.google.gson.Gson;

@Service
public class AuthService {
	
	@Autowired
	private JwtIO jwtIO;
	@Autowired
	private DateUtils dateUtils;
	@Autowired
	private TypesEncription typeCode;	
	private Utils util = new Utils();	
	@Value("${efv.jwt.token.expires-in}")
	private int EXPIRES_IN;	
	
	ConexionBD conexion = new ConexionBD();	
	private static final Logger LOG = Logger.getLogger(AuthService.class.getName());
	public ResponseEntity<ApiJwtResponse> login( HttpServletRequest request) throws Exception  {
		
		LOG.info(ConstantesJwt.Oauth.log.PROCESS_BEGIN +"["+ AuthService.class.getName()+ "] | ipConsumidor:"  +  util.getClientIpAddress(request) +" |");
		// Ir a la base datos
		JwtResponse jwt = null;
		ApiJwtResponse response= null;
		ResponseEntity<ApiJwtResponse> apiResponse = null;
		HttpSession session=request.getSession();
		
		//recuperar el consumer Key y el consumer Secret		
		String extract= typeCode.desencriptaB64(request.getHeader("Authorization").replace("Basic ", ""));		
		String username = extract.substring(0, extract.indexOf(":"));
		String  password = extract.substring( extract.indexOf(":")+1, extract.length());;
		
		try {			
			conexion.validaCredencial(username, password);
			jwt = JwtResponse.builder().tokenType("bearer")
					.accessToken(jwtIO.generateToken(password))					
					.issuedAt(dateUtils.getDateMillis() + "")
					.expiresIn(EXPIRES_IN).build();	
			response = new ApiResponse(ConstantesJwt.Codes.OK,ConstantesJwt.ApiResponses.OK,jwt);
			apiResponse  = new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.OK);
			LOG.info(ConstantesJwt.Oauth.log.PROCESS_END+" en la clase ["+ AuthService.class.getName()+ "] | ipConsumidor:"  +  util.getClientIpAddress(request) +" |" );
	       conexion.guardaBitacora(new Gson().toJson(apiResponse), util.getClientIpAddress(request));
		}catch(Exception e) {
			
			String valueError = e.getMessage();
			String error = ConstantesJwt.ApiResponses.ERROR;
			int op = 0;
			
			//Error credenciales
			if(valueError.contains(ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_EXIST )) {op = 1;}
			if(valueError.contains(ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_ACTIVE_ERROR)) {op = 2;	}
			if(valueError.contains(ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_API_ACTIVE_ERROR)) {op = 3;}
			if(valueError.contains(ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_ASOCIATE)) {op = 4;}
			
		
			switch (op) {

			case 1: 
				response = new ErrorResponse(ConstantesJwt.Codes.NOT_FOUND,ConstantesJwt.ApiResponses.FAILURE, 
						ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_EXIST );
				apiResponse  =  new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.NOT_FOUND);	
				session.setAttribute(error, ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_EXIST);
				break;
			case 2: 
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, 
						ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_ACTIVE_ERROR  );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				session.setAttribute(error, ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_ACTIVE_ERROR );
				break;
			case 3:
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE, 
						ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_API_ACTIVE_ERROR );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				session.setAttribute(error, ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_API_ACTIVE_ERROR);
				break;
			case 4:
				response = new ErrorResponse(ConstantesJwt.Codes.UNAUTHORIZED,ConstantesJwt.ApiResponses.FAILURE,
						ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_ASOCIATE );
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.UNAUTHORIZED);
				session.setAttribute(error, ConstantesJwt.Oauth.ErrorsDB.CREDENTIAL_NO_ASOCIATE);
				break;
			default:
				response = new ErrorResponse(ConstantesJwt.Codes.INTERNAL_ERROR,ConstantesJwt.ApiResponses.FAILURE, e.getMessage());
				apiResponse  =   new ResponseEntity<ApiJwtResponse>((ApiJwtResponse)response,HttpStatus.INTERNAL_SERVER_ERROR);
				session.setAttribute(error, e.getMessage());
				break;
			}
			LOG.log(Level.SEVERE, ConstantesJwt.Oauth.log.PROCESS_INTERRUPTOR +" en la clase ["+ AuthService.class.getName()+ "]  | [ detalle de error: "
					+ apiResponse.getStatusCodeValue() + " | " + session.getAttribute(error).toString() +" ]");
			conexion.guardaBitacora(new Gson().toJson(apiResponse), util.getClientIpAddress(request));			
					
		}
		return apiResponse;
	}
	}
