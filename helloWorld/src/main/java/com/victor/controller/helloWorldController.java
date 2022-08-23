package com.victor.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.dto.ApiResponse;
import com.victor.dto.SuccessResponse;
import com.victor.service.HelloWorldService;
import com.victor.utils.Validator;

@RestController
@RequestMapping({"/gestionHello"})
public class helloWorldController {

	@Autowired
	Validator val;
	@Autowired
	HelloWorldService service;
	@GetMapping("/helloWorld")
	public ResponseEntity<ApiResponse>  saludo(HttpServletRequest request) throws Exception{		
		try {
			ResponseEntity<ApiResponse> valAuth = 	val.validate(request);
			if(valAuth.getStatusCodeValue() != 200) {
				return valAuth;
			}else {
				try {
				ApiResponse res = new SuccessResponse(200,"Operacion Exitosa",service.saludar());
				return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
				}catch(Exception e) {
					throw new Exception(e.getMessage());
				}
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}		
	}
}
