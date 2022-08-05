package com.efectivale.jwtServer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel
public class JwtResponse{
	
	@ApiModelProperty(example="bearer")
	private String tokenType;	
	@ApiModelProperty(example="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTk1NTIyMjIsImlh.....etc")
	private String accessToken;		
	@ApiModelProperty(example="360")
	private int expiresIn;	
	@ApiModelProperty(example="1641400511000")
	private String issuedAt;
	@ApiModelProperty(example="user")
	private String clienteId;

}
