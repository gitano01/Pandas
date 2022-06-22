package com.example.demoHeaders;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.*")
public class DemoHeadersApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoHeadersApplication.class, args);
	}
	
	@ConfigurationProperties(prefix="spring.datasource")
	@Bean
	@Qualifier("data_pgsql")
	public DataSource getDataSource() {
		return DataSourceBuilder
				.create()
				.build();
	}

}
