package com.efectivale.jwtServer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class APIJwtBadRequest extends Exception {
	private static final long serialVersionUID = -8351404375202950212L;

	public APIJwtBadRequest(String message) {
		super(message);
	}
}
