package com.efectivale.jwtServer.utils;

public  class ConstantesJwt {
	
	
	public static class dataIP{
		public static final String[] HEADERS_TO_TRY = {
				"X-Forwarded-For",
	            "Proxy-Client-IP",
	            "WL-Proxy-Client-IP",
	            "HTTP_X_FORWARDED_FOR",
	            "HTTP_X_FORWARDED",
	            "HTTP_X_CLUSTER_CLIENT_IP",
	            "HTTP_CLIENT_IP",
	            "HTTP_FORWARDED_FOR",
	            "HTTP_FORWARDED",
	            "HTTP_VIA",
	            "REMOTE_ADDR"
		};
	}
	
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
			public static final  String API_NOTFOUND = "EL API A CONSUMIR NO EXISTE";
			public static final String API_ACTIVE_ERROR = "EL API A CONSUMIR NO SE ENCUENTRA ACTIVA, CONSULTA CON TU ADMINISTRADOR";
			public static final String API_NOCREDENTIALS = "EL API A CONSUMIR NO TIENE ASOCIACIÓN CON LA CREDENCIAL DEL USUARIO";
			}
		
		public static class log{
			public static String PROCESS_BEGIN = "Inicia proceso de la clase:  ";
			public static String PROCESS_END = "Termina proceso con éxito de la clase: ";
			public static String PROCESS_INTERRUPTOR = "Proceso interrumpido ";			
		}
		
	}
	
	public static class Params{
		public static final String GRANT_TYPE = "grant_type";
		public  static final String CLIENT_CREDENTIALS = "client_credentials";
		public static final String API = "api" ;		
	}
	public static class ParamsError{
		public static final String GRANT_TYPE_ERROR = "El tipo de granType está vacío o no es el requerido";
		public static final String USER_ERROR = "Usuario y/ o Contraseña incorrectos" ;
		public static final String API_ERROR = "El parametro api es requerido" ;
		
	}
	
	public static class Swagger{
		   public static final String TITLE = "JWT Token Server Auth";
		   public static final String DESCRIPTION = "Documentación para la autenticacion de usuarios en aplicaciones de efectivale mediante tokens";
		   public static final String  CONTROLLER_DESCRIPTION = "Genera un token para la autenticacion en otras aplicaciones con configuracion de seguridad";
		   public static final String VERSION = "0.0.1";
		   public static final String LICENSE = "Apache 2.0";
		   public static final String LICENSESURL = "https://www.apache.org/licenses/LICENSE-2.0\"";   
		   
		   //Repuestas para la vista del swagger
		   public static final String OK = "Ok";
			public static final String BAD_REQUEST = "Petición fallida";
			public static final String UNAUTHORIZED = "No Autorizado";
			public static final String NOT_FOUND = "Recurso no encontrado";
			public static final String INTERNAL_ERROR = "Error Interno del Servidor";
	}
	public static class Codes{
		//codigo de repuesta
		public static final int OK = 200;
		public static final int BAD_REQUEST = 400;
		public static final int UNAUTHORIZED = 401;
		public static final int NOT_FOUND = 404;
		public static final int INTERNAL_ERROR = 500;
		}
	public static class ApiResponses{		
		// Strings Respuestas
		public static final String OK = "Operación Exitosa";
		public static final String FAILURE = "Operación fallida";
		
		
	}
}
