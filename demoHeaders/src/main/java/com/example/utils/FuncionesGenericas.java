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
		List<CredencialesResponse> lista = obtenerCredenciales();
		if (lista != null) {

			for (CredencialesResponse elem : lista) {

				if (credenciales.getUsuario().equals(elem.getCredencialUsuario())) {

					return "El usuario ingresado ya se encuentra registrado en la base de datos";
				}
			}
		}

		call = new SimpleJdbcCall(dataSourceConf).withFunctionName("insertarCredencial");
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("usuario", credenciales.getUsuario());
		in.addValue("contrasenia", credenciales.getContrasenia());
		in.addValue("consumerkey", credenciales.getConsumerKey());
		in.addValue("consumersecret", credenciales.getConsumerSecret());
		result = call.execute(in).toString();

		return result;

	}

	public List<CredencialesResponse> obtenerCredenciales() {

		String sql = "select * FROM getcredenciales()";

		List<CredencialesResponse> list = this.jdbcTemplate.query(sql, new RowMapper<CredencialesResponse>() {
			@Override
			public CredencialesResponse mapRow(ResultSet rs, int i) throws SQLException {
				CredencialesResponse credencial = new CredencialesResponse();

				credencial.setCredencialid(rs.getLong("credencialid"));
				credencial.setCredencialUsuario(rs.getString("credencialusuario"));
				credencial.setCredencialPassword(rs.getString("credencialpassword"));
				credencial.setCredencialConsumerKey(rs.getString("credencialconsumerkey"));
				credencial.setCredencialConsumerSecret(rs.getString("credencialconsumersecret"));
				credencial.setCredencialFechaCreacion(rs.getString("credencialfechacreacion"));
				System.out.println(rs.getString("credencialfechamodificacion"));
				if (rs.getString("credencialfechamodificacion") == "") {
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
