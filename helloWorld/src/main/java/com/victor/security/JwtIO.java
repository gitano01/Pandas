package com.victor.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.victor.dto.DataUser;

import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Component
public class JwtIO {
	
//	@Value("${victor.jwt.token.secret:secret}")
//	private String SECRET;
	
	
	public boolean validateToken(String encode){		
		JWT jwt = jwt(encode);
		System.out.println(jwt);
//		DataUser userData = new Gson().fromJson(getPayload(encode), DataUser.class);
//		
//		String cad = null;
//		if (userData.getRs_consumersecret().contains("\u003d")){
//			userData.setRs_consumersecret(userData.getRs_consumersecret().replace("\u003d", "="));
//		}
//		System.out.println(userData.toString());
		return jwt.isExpired();
	}
	
	public String getPayload(String encodedJWT) {
		
		JWT jwt = jwt(encodedJWT);
		return jwt.subject;
	}
	
	private  JWT jwt(String encodedJWT) {		
		//Verifier verifier = HMACVerifier.newVerifier(SECRET);
		Verifier verifier = HMACVerifier.newVerifier("c3ViKjEzNXUqbzMxKm91YkwwMzA=");
		JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);
		
		return jwt;
	}

	
}
