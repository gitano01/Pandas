package com.example.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.RowMapper;

import com.example.models.CredencialesGeneradas;
import com.example.models.CredencialesResponse;
import com.example.jdbc.JdbcConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class FuncionesGenericas {

	JdbcConfig jdbcConfig = new JdbcConfig();
	DataSource dataSourceConf = jdbcConfig.myPgSqlDataSource();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConf);	 
	SimpleJdbcCall call;
	
	public String generador(char[] chars) {
		StringBuilder sb = new StringBuilder(20);
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		//output = sb.toString();
		System.out.println(Base64.getEncoder().encodeToString(sb.toString().getBytes()));
		
		return Base64.getEncoder().encodeToString(sb.toString().getBytes());
	}
	
	
//	public String asignarCredenciales(CredencialesGeneradas credenciales) {
//		
//		List<CredencialesResponse> lista = obtenerCredenciales(credenciales);
//		
////		for(CredencialesResponse elem: lista) {
////			
////			if(credenciales.getUsuario() == elem.getCredencialUsuario()) {
////				
////			}
////		}
//		
//		
//		
//	}
	
	
	public List<CredencialesResponse> obtenerCredenciales(CredencialesGeneradas credenciales){
		
		String  sql = "select getcredenciales()";
		 
	    List<CredencialesResponse>  list = this.jdbcTemplate.query(sql, new RowMapper<CredencialesResponse>() {
	        @Override
	        public CredencialesResponse mapRow(ResultSet rs, int i) throws SQLException {
	        	CredencialesResponse credencial = new CredencialesResponse();
	            
	        	credencial.setCredencialid(rs.getLong("credencialid"));
	        	credencial.setCredencialusuario(rs.getString("credencialusuario"));
	        	credencial.setCredencialconsumerkey(rs.getString("credencialconsumerkey"));
	        	credencial.setCredencialconsumersecret(rs.getString("credencialconsumersecret"));
	        	credencial.setCredencialfechacreacion(rs.getString("credencialfechacreacion"));
	        	
	        	if(rs.getString("credencialfechamodificacion") == null) {
	        		credencial.setCredencialfechamodificacion("");
	        	}else {
	        		credencial.setCredencialfechamodificacion(rs.getString("credencialfechamodificacion"));
	        	}
	        	credencial.setCredenciaactiva(rs.getBoolean("credencialactiva"));
	           	            
	            return credencial;
	        }
	        
	    });
	    
	    return list;
	    
	    
	}
}
