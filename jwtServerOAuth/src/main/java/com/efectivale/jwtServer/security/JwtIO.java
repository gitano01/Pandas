package com.efectivale.jwtServer.security;

import java.time.ZonedDateTime;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.efectivale.jwtServer.utils.GsonUtils;
import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;

@Component
public class JwtIO {
	
	@Value("${efv.jwt.token.secret:secret}")
	private String SECRET;
	@Value("${efv.jwt.timezone:UTC}")
	private String TIMEZONE;
	@Value("${efv.jwt.token.expires-in:1800}")
	private int EXPIRE_IN;
	@Value("${efv.jwt.issuer:none}")
	private String ISSUER;
	
	public String generateToken(Object src){
		
		String subject = GsonUtils.serialize(src);
		//Construye el HMAC usando SHA256
		Signer signer = HMACSigner.newSHA256Signer(SECRET);
		TimeZone tz = TimeZone.getTimeZone(TIMEZONE);
		ZonedDateTime zdt = ZonedDateTime.now(tz.toZoneId()).plusSeconds(EXPIRE_IN);
		
		JWT jwt = new JWT().setIssuer(ISSUER)
				.setIssuedAt(ZonedDateTime.now(tz.toZoneId()))
				.setSubject(subject)
				.setExpiration(zdt);
		
		return jwt.getEncoder().encode(jwt, signer);
	}
	
	public boolean validateToken(String encode){		
		JWT jwt = jwt(encode);		
		return jwt.isExpired();
	}
	
	public String getPayload(String encodedJWT) {
		
		JWT jwt = jwt(encodedJWT);
		return jwt.subject;
	}
	
	private JWT jwt(String encodedJWT) {
		
		Verifier verifier = HMACVerifier.newVerifier(SECRET);		
		return JWT.getDecoder().decode(encodedJWT, verifier);
	}

	
}
