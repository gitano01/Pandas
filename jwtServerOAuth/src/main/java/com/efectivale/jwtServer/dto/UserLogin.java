package com.efectivale.jwtServer.dto;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="datos del usuario")
public class UserLogin {
	
	@ApiModelProperty(example="user",required=true)
	private  String username;
	@ApiModelProperty(example="password123",required=true)
	private  String password;
}
