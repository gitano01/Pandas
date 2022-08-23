package com.victor.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Component
public class JwtIO {
	
@Value("${victor.jwt.token.secret:secret}")
private String SECRET;
	
	public boolean validateToken(String encode){		
		JWT jwt = jwt(encode);
		return jwt.isExpired();
	}
	
	private JWT jwt(String encodedJWT) {		
		Verifier verifier = HMACVerifier.newVerifier(SECRET);
		//Verifier verifier = HMACVerifier.newVerifier("c3ViKjEzNXUqbzMxKm91YkwwMzA=");
		JWT jwt = JWT.getDecoder().decode(encodedJWT, verifier);
		
		return jwt;
	}

	
}
