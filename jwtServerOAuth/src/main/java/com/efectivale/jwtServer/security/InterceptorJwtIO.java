package com.efectivale.jwtServer.security;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {

	@Value("${efv.jwt.token.auth.path}")
	private String AUTH_PATH;
	@Value("#{'${efv.jwt.excluded.path}'}")
	private List<String> excluded;
	
	@Autowired
	private JwtIO jwtIO;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean validate = false;
		String url =  request.getRequestURI();
		
		if(url.equals(AUTH_PATH)||excluded(url)){
			validate = true;
		}
		
		return validate;
	}
	
	private boolean excluded(String path){
		boolean result = false;
		
		for(String exc: excluded){			
			result = (!exc.equals("#")&&exc.equals(path)) ? true: false;
		}
		
		return result;
	}

	
}
