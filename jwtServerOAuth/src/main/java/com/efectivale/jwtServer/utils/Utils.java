package com.efectivale.jwtServer.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	public String getClientIpAddress(HttpServletRequest request) {
	    for (String header : ConstantesJwt.dataIP.HEADERS_TO_TRY) {
	        String ip = request.getHeader(header);
	        if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	            return ip;
	        }
	    }
	    return request.getRemoteAddr();
	}
}
