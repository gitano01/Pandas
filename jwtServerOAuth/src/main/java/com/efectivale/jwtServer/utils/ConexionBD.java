package com.efectivale.jwtServer.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import com.efectivale.jwtServer.jdbcconfig.JdbcConfig;

public class ConexionBD {
	private JdbcConfig jdbcConfig = new JdbcConfig();
	DataSource dataSourceConf = jdbcConfig.myPgSqlDataSource();
	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConf);
	SimpleJdbcCall call;

	public boolean validaCredencial(String consumer, String secretkey) throws SQLException {

		boolean validaCred = false;
		Map<String, Object> result = null;
		call = new SimpleJdbcCall(dataSourceConf).withFunctionName("sp_validacredencial");
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("consumer", consumer);
		in.addValue("secretkey", secretkey);
		result = call.execute(in);
		if (result.get("returnvalue").equals(true)) {
			validaCred = true;
		}
		return validaCred;
	}

	public void guardaBitacora(String response, String ipConsumidor) throws Exception, SQLException {

		call = new SimpleJdbcCall(dataSourceConf).withFunctionName("tok_ins_bitacora");
		MapSqlParameterSource in = new MapSqlParameterSource();
		in.addValue("dateconsumed", new Date());
		in.addValue("details", response);
		in.addValue("remote_address", ipConsumidor);
		call.execute(in);
	}

}
