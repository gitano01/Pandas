package com.efectivale.jwtServer.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import com.efectivale.jwtServer.dto.DataUser;
import com.efectivale.jwtServer.dto.JwtResponse;
import com.efectivale.jwtServer.security.JwtIO;
import com.efectivale.jwtServer.utils.DateUtils;
import com.google.gson.Gson;

import jdbcconfig.JdbcConfig;

@Service
public class AuthService {

	@Autowired
	private JwtIO jwtIO;
	@Autowired
	private DateUtils dateUtils;
	@Value("${efv.jwt.token.expires-in}")
	private int EXPIRES_IN;

	
	private JdbcConfig jdbcConfig = new JdbcConfig();

	DataSource dataSourceConf = jdbcConfig.myPgSqlDataSource();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConf);
	SimpleJdbcCall call;

	public JwtResponse login(String username, String password, String servicio)  throws Exception{

		// Ir a la base datos
		DataUser dUser = null;
		try {
	String sql = "SELECT  * FROM tok_usuario_app_keys('" + username +"', '" + password +"','" + servicio+ "') ";

			List<DataUser> datosUsuario = this.jdbcTemplate.query(sql, new RowMapper<DataUser>() {
				@Override
				public DataUser mapRow(ResultSet rs, int i) throws SQLException {
					DataUser datos = new DataUser();
					datos.setRs_username(rs.getString("rs_username"));
					datos.setRs_password(rs.getString("rs_password"));
					datos.setRs_credencialid(rs.getInt("rs_credencialid"));
					datos.setRs_consumerkey(rs.getString("rs_consumerkey"));
					datos.setRs_consumersecret(rs.getString("rs_consumersecret"));
					datos.setRs_servicio(rs.getString("rs_servicio"));
					datos.setRs_url(rs.getString("rs_url"));
					return datos;
				}
			});
			if(datosUsuario.size() == 0) {
				throw new Exception("No existen registros");
			}else {
				for(DataUser usr: datosUsuario) {
					dUser = usr;
				}
			}
			
			JwtResponse jwt = JwtResponse.builder().tokenType("bearer")
					.accessToken(jwtIO.generateToken(dUser)).issuedAt(dateUtils.getDateMillis() + "")
					.clienteId(username).expiresIn(EXPIRES_IN).build();

			return jwt;

		} catch (Exception e) {

		}
		return null;
	}
}
