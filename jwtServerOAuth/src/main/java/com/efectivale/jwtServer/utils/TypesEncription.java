package com.efectivale.jwtServer.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class TypesEncription {
	
	public String encriptaMD5(String pass) throws Exception{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(pass));
        return String.format("%032x", new BigInteger(1, md5.digest()));
	}
	
	public String desencriptaB64(String pass) throws Exception{		
		try {
		byte[] decodedBytes = Base64.getDecoder().decode(pass); 		
		return new String(decodedBytes);
		}catch(Exception e) {
			throw new Exception (e.getMessage());
		}
	}
}
