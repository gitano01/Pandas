package com.efectivale.jwtServer.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.efectivale.jwtServer.utils.ConstantesJwt;

import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.models.security.SecurityScheme;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.select().apis(RequestHandlerSelectors.basePackage("com.efectivale.jwtServer.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metaData())
				.securitySchemes(Arrays.asList(basicAuthScheme()))
				.useDefaultResponseMessages(false);
		return docket;
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title(ConstantesJwt.Swagger.TITLE).description(ConstantesJwt.Swagger.DESCRIPTION)
				.version(ConstantesJwt.Swagger.VERSION).license(ConstantesJwt.Swagger.LICENSE)
				.licenseUrl(ConstantesJwt.Swagger.LICENSESURL).build();
	}

    private BasicAuth basicAuthScheme() {
        return new BasicAuth("basicAuth");
    }
}
