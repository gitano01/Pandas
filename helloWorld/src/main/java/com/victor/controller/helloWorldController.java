package com.victor.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.service.HelloWorldService;

@RestController
@RequestMapping({"/gestionHello"})
public class helloWorldController {

	@Autowired
	HelloWorldService service;
	@GetMapping("/helloWorld")
	public ResponseEntity<Map<String,String>>  saludo() throws Exception{		
		try{
			
			Map<String , String >  mapaOk = new HashMap<String,String>();
			mapaOk.put("saludo", service.saludar());			
		return new ResponseEntity<Map<String,String>>(mapaOk, HttpStatus.OK);
		}catch(Exception e) {
			Map<String , String >  mapaOk = new HashMap<String,String>();
			mapaOk.put("error", e.getMessage());			
			return new ResponseEntity<Map<String,String>>(mapaOk, HttpStatus.UNAUTHORIZED);
		}
	}
}
