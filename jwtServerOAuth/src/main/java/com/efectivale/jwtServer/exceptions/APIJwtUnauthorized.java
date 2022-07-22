package com.efectivale.jwtServer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class APIJwtUnauthorized extends Exception {

	private static final long serialVersionUID = -8351404375202950212L;

	public APIJwtUnauthorized(String message) {
		super(message);
	}
}
