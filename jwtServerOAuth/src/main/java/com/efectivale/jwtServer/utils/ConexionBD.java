package com.efectivale.jwtServer.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

	public DataUser datosCredencial(String username, String password, String nombreApi) throws Exception, SQLException {

		DataUser dUser = null;
		try {
			// String passEnc = enc.encripta(password);
			String sql = "SELECT  * FROM sp_datoscredencial('" + username + "', '" + enc.encripta(password) + "','"
					+ nombreApi + "') ";
			// String sql = "SELECT * FROM sp_datoscredencial('" + username +"',
			// '91e4cc027fee6ca8de00f8101a507210','" + nombreApi+ "') ";

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

			for (DataUser usr : datosUsuario) {
				dUser = usr;
			}
			return dUser;

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
	}

	public void guardaBitacora(String username, String password, String nombreApi, String response, String ipConsumidor,
			HttpSession session) throws Exception, SQLException {
		List<String> keySession = new ArrayList<String>();
		if (session != null) {
			Enumeration<String> e = session.getAttributeNames();
			while (e.hasMoreElements()) {

				keySession.add((String) e.nextElement());
			}
		}

		if (keySession.contains("id")) {
			call = new SimpleJdbcCall(dataSourceConf).withFunctionName("tok_ins_bitacora");
			MapSqlParameterSource in = new MapSqlParameterSource();
			in.addValue("userid", null);
			in.addValue("servicio", nombreApi);
			in.addValue("dateconsumed", new Date());
			in.addValue("details", response);
			in.addValue("remote_address", ipConsumidor);
			call.execute(in);
			session.removeAttribute("id");
		} else {

			// ir por id del usuario
			String sql = "SELECT user_id FROM wsuser WHERE username = " + "'" + username + "'" + " AND password = "
					+ "'" + enc.encripta(password) + "'";
			int idUser = jdbcTemplate.queryForObject(sql, Integer.class);
			call = new SimpleJdbcCall(dataSourceConf).withFunctionName("tok_ins_bitacora");
			MapSqlParameterSource in = new MapSqlParameterSource();
			in.addValue("userid", idUser);
			in.addValue("servicio", nombreApi);
			in.addValue("dateconsumed", new Date());
			in.addValue("details", response);
			in.addValue("remote_address", ipConsumidor);
			call.execute(in);
		}

	}

}
