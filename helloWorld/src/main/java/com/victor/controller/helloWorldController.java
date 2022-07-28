package com.victor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.victor.service.HelloWorldService;

@RestController
@RequestMapping({"/gestionHello"})
public class helloWorldController {

	@Autowired
	HelloWorldService service;
	@GetMapping("/helloWorld")
	public @ResponseBody String saludo(){
		return service.saludar();
	}
}
