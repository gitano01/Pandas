package com.jwt.jwtServices.dao.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.jwt.jwtServices.dao.implement.JwtImplement;
import com.jwt.jwtServices.model.ApiResponse;

@Service
public class JwtService implements JwtImplement {

	public ResponseEntity<ApiResponse> getJwToken(Map<String, String> map) {
		ApiResponse res = null;
		ResponseEntity<ApiResponse> result = null;
		try {

			String url = "http://localhost:8081/oauth/token";

			String urlTemplate = UriComponentsBuilder.fromHttpUrl(url).queryParam("grant_type", map.get("grant_type"))
					.queryParam("username", map.get("username")).queryParam("password", map.get("password")).encode()
					.toUriString();

			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();

			headers.setBasicAuth(map.get("client_id"), map.get("client_secret"));

			HttpEntity<?> entity = new HttpEntity<>(headers);

			ResponseEntity<?> resp = restTemplate.exchange(urlTemplate, HttpMethod.POST, entity, String.class, map);

			String jsonString = EntityUtils.toString((org.apache.http.HttpEntity) resp.getBody());
			JSONPObject jsonObject = new JSONObject(jsonString);
			
			res = new ApiResponse("200", "Operación Exitosa", (Object) resp.getBody());

			return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);

		} catch (Exception ex) {

			String code = ex.getMessage().substring(0, 3);

			switch (code) {
			case "400":
				res = new ApiResponse("400", "Operación fallida", ex.getMessage());
				result = new ResponseEntity<ApiResponse>(res, HttpStatus.BAD_REQUEST);
				break;
			case "401":
				res = new ApiResponse("401", "Operación no autorizada", ex.getMessage());
				result = new ResponseEntity<ApiResponse>(res, HttpStatus.UNAUTHORIZED);
				break;
			case "404":
				res = new ApiResponse("404", "Recurso no encontrado", ex.getMessage());
				result = new ResponseEntity<ApiResponse>(res, HttpStatus.BAD_REQUEST);
				break;
			default:
				res = new ApiResponse("500", "Error interno", ex.getMessage());
				result = new ResponseEntity<ApiResponse>(res, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return result;
		}

	}

}
