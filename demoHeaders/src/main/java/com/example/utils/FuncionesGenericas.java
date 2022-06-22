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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

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
		// output = sb.toString();
		System.out.println(Base64.getEncoder().encodeToString(sb.toString().getBytes()));

		return Base64.getEncoder().encodeToString(sb.toString().getBytes());
	}

	public String asignarCredenciales(CredencialesGeneradas credenciales) {

		String result = null;
		List<CredencialesResponse> lista = obtenerCredenciales(credenciales);

		for (CredencialesResponse elem : lista) {

			if (credenciales.getUsuario() == elem.getCredencialUsuario()) {

				return "El usuario ingresado ya se encuentra registrado en la base de datos";
			} else {

				call = new SimpleJdbcCall(dataSourceConf).withFunctionName("insertarCredencial");
				MapSqlParameterSource in = new MapSqlParameterSource();
				in.addValue("nombre", credenciales.getUsuario());
				in.addValue("credencialconsumerkey", credenciales.getConsumerKey());
				in.addValue("credencialconsumersecret", credenciales.getConsumerSecret());
				result = call.execute(in).toString();
			}
		}

		return result;

	}

	public List<CredencialesResponse> obtenerCredenciales(CredencialesGeneradas credenciales) {

		String sql = "select getcredenciales()";

		List<CredencialesResponse> list = this.jdbcTemplate.query(sql, new RowMapper<CredencialesResponse>() {
			@Override
			public CredencialesResponse mapRow(ResultSet rs, int i) throws SQLException {
				CredencialesResponse credencial = new CredencialesResponse();

				credencial.setCredencialid(rs.getLong("credencialid"));
				credencial.setCredencialUsuario(rs.getString("credencialusuario"));
				credencial.setCredencialConsumerKey(rs.getString("credencialconsumerkey"));
				credencial.setCredencialConsumerSecret(rs.getString("credencialconsumersecret"));
				credencial.setCredencialConsumerSecret(rs.getString("credencialconsumersecret"));
				credencial.setCredencialFechaCreacion(rs.getString("credencialfechacreacion"));

				if (rs.getString("credencialfechamodificacion") == null) {
					credencial.setCredencialFechaModificacion("");
				} else {
					credencial.setCredencialFechaModificacion(rs.getString("credencialfechamodificacion"));
				}
				credencial.setCredenciaActiva(rs.getBoolean("credencialactiva"));

				return credencial;
			}

		});

		return list;

	}
}
