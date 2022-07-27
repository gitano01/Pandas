package com.efectivale.jwtServer.utils;


public  class ConstantesJwt {
	
	public static class Oauth{
		public static final   String GENERATION_TOKEN = "oauth/client_credential/accesstoken";
		public static final  String VALIDATION_TOKEN = "oauth/validateaccesstoken";
	}
	
	public static class Params{
		public static final String GRANT_TYPE = "grant_type";
		public static final String USERNAME = "username";
		public static final String PASSWORD = "password";
		public static final String SERVICE = "servicio" ;
		
	}
}
