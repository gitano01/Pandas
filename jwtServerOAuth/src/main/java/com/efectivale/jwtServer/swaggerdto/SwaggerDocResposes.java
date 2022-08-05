package com.efectivale.jwtServer.swaggerdto;

import com.efectivale.jwtServer.utils.ConstantesJwt;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

public class SwaggerDocResposes {

	@Data
	@ApiModel(description="Resultado de ejemplo que se mostrará en caso de éxito")	
	public class ApiJwtMockResponse{	
		@ApiModelProperty(notes="Estado de la operación", example="200")
		public int codigo;
		@ApiModelProperty(notes="Mensaje de la operación", example=ConstantesJwt.ApiResponses.OK)
		public String mensaje;
		@JsonPropertyOrder(alphabetic=false)
		public JwtResponse responseToken;
		@ApiModel(description="Resultado que se mostrará cuando se generé el token")
		public class JwtResponse{
			@ApiModelProperty(notes="Tipo de token", example="bearer")
			public String tokenType;
			@ApiModelProperty(notes="Valor del token", example="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NTk3MTI4MDMsImlhdCI6MTY1OTcxMjQ0MywiaXNzIjoiaHR0cHM6Ly93d3cuZWZlY3RpdmFsZS5jb20ubXgvIiwic3ViIjoie1wiYXBpXCI6XCJqd3RUb2tlblNlcnZpY2VcIixcImNyZWRlbmNpYWxBY3RpdmFcIjpcInRcIn0ifQ.NZRmsMa93Y5a57fjv4rMwwZvG_LPaia6NeGPKjMWrGc")
			public String acessToken;
			@ApiModelProperty(notes="Tiempo de expiración del token", example="360")
			public int expiresIn;
			@ApiModelProperty(notes="Expedicion del token en base al sistema", example="1641395643000")
			public String issuedAt;
			@ApiModelProperty(notes="Usuario que generá solicitud de token", example="user123")
			public String clienteId;
		}
	}
	
	@ApiModel(description="Resultado presentado al usuario en caso de generarse un error de validación")
	public class Error400{
		@ApiModelProperty(notes="Estado de la operación", example="400")
		public int codigo;
		@ApiModelProperty(notes="Mensaje de la operación", example=ConstantesJwt.ApiResponses.FAILURE)
		public String mensaje;
		@ApiModelProperty(notes="Detalle de error en la operación", example= "Describe el valor de error de validación" )
		public String detalle;		
	}
	
	@ApiModel(description="Resultado presentado al usuario en caso de generarse un error de autorización")
	public class Error401{
		@ApiModelProperty(notes="Estado de la operación", example="401")
		public int codigo;
		@ApiModelProperty(notes="Mensaje de la operación", example=ConstantesJwt.ApiResponses.FAILURE)
		public String mensaje;
		@ApiModelProperty(notes="Detalle de error en la operación", example= ConstantesJwt.Swagger.UNAUTHORIZED )
		public String detalle;		
	}
	
	@ApiModel(description="Resultado presentado al usuario en caso de generarse un error sobre el recurso")
	public class Error404{
		@ApiModelProperty(notes="Estado de la operación", example="404")
		public int codigo;
		@ApiModelProperty(notes="Mensaje de la operación", example=ConstantesJwt.ApiResponses.FAILURE)
		public String mensaje;
		@ApiModelProperty(notes="Detalle de error en la operación", example= ConstantesJwt.Swagger.NOT_FOUND )
		public String error;		
	}
	
	@ApiModel(description="Resultado presentado al usuario en caso de generarse un error de validación")
	public class Error500{
		@ApiModelProperty(notes="Estado de la operación", example="500")
		public int codigo;
		@ApiModelProperty(notes="Mensaje de la operación", example=ConstantesJwt.ApiResponses.FAILURE)
		public String mensaje;
		@ApiModelProperty(notes="Detalle de error en la operación", example= ConstantesJwt.Swagger.INTERNAL_ERROR )
		public String detalle;		
	}
	
	

}