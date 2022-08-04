package com.efectivale.jwtServer.utils;


public  class ConstantesJwt {
	
	public static class Oauth{
		public static final String VERSION = "v1";
		public static final   String GENERATION_TOKEN = "oauth/client_credential/accesstoken";
		
	
		public static class errorsDB{
			
			//errores usuario
			public static final String USERNAMEPASSWORD_ERROR = "USUARIO Y/O CONTRASEÑAS INCORRECTOS";
			public static final String USERNAMEACTIVE_ERROR = "EL USUARIO NO SE ENCUENTRA ACTIVO, CONSULTA CON TU ADMINISTRADOR";
			public static final String USERNAME_NOCREDENTIALS = "EL USUARIO NO TIENE ASOCIADA A UNA CREDENCIAL"; 
			//errores credencial
			public static final String CREDENTIAL_USER_ACTIVE_ERROR = "LA CREDENCIAL DEL USUARIO NO SE ENCUENTRA ACTIVA, CONSULTA CON TU ADMINISTRADOR";
			public static final String CREDENTIAL_API_ACTIVE_ERROR = "LA CREDENCIAL NO ESTA ACTIVA PARA EL CONSUMO DEL API SOLICITADA";			
			//errores api
			public static final  String API_NOTFOUND = "EL SERVICIO A CONSUMIR NO SE ENCUENTRA EN LOS REGISTROS";
			public static final String API_ACTIVE_ERROR = "EL API A CONSUMIR NO SE ENCUENTRA ACTIVA, CONSULTA CON TU ADMINISTRADOR";
			public static final String API_NOCREDENTIALS = "EL API A CONSUMIR NO TIENE ASOCIACIÓN CON LA CREDENCIAL DEL USUARIO";
			
		}
	
	
	}
	
	public static class Params{
		public static final String GRANT_TYPE = "grant_type";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String SERVICE = "servicio" ;		
	}
	
	public static class Swagger{
		   public static final String TITLE = "JWT Token Server Auth";
		   public static final String DESCRIPTION = "Documentación para la autenticacion de usuarios en aplicaciones de efectivale mediante tokens";
		   public static final String VERSION = "0.0.1";
		   public static final String LICENSE = "Apache 2.0";
		   public static final String LICENSESURL = "https://www.apache.org/licenses/LICENSE-2.0\"";   
	}
	
	
	
}
