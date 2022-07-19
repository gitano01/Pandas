package com.jwt.jwtServices.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/at")
public class jwtController {

	@PostMapping("/oauth/token")
	public @ResponseBody ResponseEntity<String> obtenerToken(@RequestParam Map<String,String> parameters) throws Exception{
		
		String url = "http://localhost:8081/oauth/token";
		String authStr = "cli1:cli1";
		String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
		System.out.println(parameters.toString());
//		 // create headers
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.add("Authorization", "Basic " + base64Creds);
//			 
//		
//		RestTemplate restTemplate = new RestTemplate();
//		
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);
//
//		ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
//		
//		// get JSON response
//	    String json = response.getBody();
//
//		return new ResponseEntity<String>(json, HttpStatus.OK);
		
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        
        

        headers.setAll(map);

        Map req_payload = new HashMap();
        req_payload.put("grant_type", "password");
        req_payload.put("username", "admin");
        req_payload.put("password", "password");
//        req_payload.put("name", "piyush");

        HttpEntity<?> request = new HttpEntity<>(req_payload, headers);
//        String url = "http://localhost:8080/xxx/xxx/";

        ResponseEntity<?> response = new RestTemplate().postForEntity(url, request, String.class);
//        ServiceResponse entityResponse = (ServiceResponse) response.getBody();
        System.out.println(response.getBody().toString());
        return new ResponseEntity<String>(response.getBody().toString(), HttpStatus.OK);
		
	}

	
}
