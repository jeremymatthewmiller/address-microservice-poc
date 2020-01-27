package com.grainger.address.config;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.grainger.address.controller"))
            .paths(PathSelectors.any())
            .build()
            //.securitySchemes(Arrays.asList(securityScheme()))
	        //.securityContexts(Arrays.asList(securityContext()))
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500)
                .message("HTTP 500 Error Message")
                .responseModel(new ModelRef("Error"))
                .build(),
                new ResponseMessageBuilder().code(403)
                    .message("Forbidden!")
                    .build()));
    }
/*
	@Bean
	public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()
	        .clientId(CLIENT_ID)
	        .clientSecret(CLIENT_SECRET)
	        .scopeSeparator(" ")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
*/
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("Grainger CIM Address Validation POC API", "The Grainger CIM Address Validation POC API description.", "API TOS", "Terms of service", new Contact("Jeremy", "www.grainger.com", "grainger@grainger.com"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
}
