package com.efectivale.jwtServer.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import com.efectivale.jwtServer.dto.DataUser;
import com.efectivale.jwtServer.jdbcconfig.JdbcConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;




public class ConexionBD {
	private JdbcConfig jdbcConfig = new JdbcConfig();
	DataSource dataSourceConf = jdbcConfig.myPgSqlDataSource();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConf);
	SimpleJdbcCall call;
	private TypesEncription enc = new TypesEncription();
		public DataUser datosCredencial(String username, String password, String nombreApi) throws Exception, SQLException{
			
			DataUser dUser = null;	
			try {
				//String passEnc = enc.encripta(password);
				String sql = "SELECT  * FROM sp_datoscredencial('" + username +"', '" + enc.encripta(password) +"','" + nombreApi+ "') ";
				//String sql = "SELECT  * FROM sp_datoscredencial('" + username +"', '91e4cc027fee6ca8de00f8101a507210','" + nombreApi+ "') ";
				
						List<DataUser> datosUsuario = this.jdbcTemplate.query(sql, new RowMapper<DataUser>() {
							@Override
							public DataUser mapRow(ResultSet rs, int i) throws SQLException {
								DataUser datos = new DataUser();
								Gson gson = new GsonBuilder().disableHtmlEscaping().create();
								String secret = gson.toJson(rs.getString("rs_consumersecret"));
								datos.setRs_consumersecret(secret);
								datos.setRs_api(rs.getString("rs_api"));
								datos.setRs_credencial_activa(rs.getString("rs_credencial_activa"));
								return datos;
							}
						});
						
						for(DataUser usr: datosUsuario) {
							dUser = usr;
						}							
						return dUser;
						
					} catch (SQLException e) {	
						throw new SQLException(e.getMessage());
					}
		}
	
}
