package com.diagnostico.api.config.swagger;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.diagnostico.api.config.property.DiagnosisApiProperty;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Autowired
	private DiagnosisApiProperty property;

	@Bean
	public Docket detalheApi() {

		Docket docket = new Docket(DocumentationType.SWAGGER_2);

		docket.select().apis(RequestHandlerSelectors.basePackage("com.diagnostico.api")).paths(PathSelectors.any())
				.build().apiInfo(this.informationsApi().build())
				.securitySchemes(Arrays.asList(securityScheme()))
		        .securityContexts(Arrays.asList(securityContext()));

		return docket;
	}
	 
	@Bean
	public SecurityConfiguration security() {
	    return springfox.documentation.swagger.web.SecurityConfigurationBuilder.builder()
	        .clientId("")
	        .clientSecret("")
	        .scopeSeparator(" ")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
	
	private SecurityScheme securityScheme() {
	    GrantType grantType = new AuthorizationCodeGrantBuilder()
	        .tokenEndpoint(new TokenEndpoint(property.getOriginPermitted() + "/oauth/token", "oauthtoken"))
	        .tokenRequestEndpoint(
	          new TokenRequestEndpoint(property.getOriginPermitted() + "/oauth/authorize", "", ""))
	        .build();
	 
	    SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
	        .grantTypes(Arrays.asList(grantType))
	        .scopes(Arrays.asList(scopes()))
	        .build();
	    return oauth;
	}
	
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("read", "for read operations"), 
	      new AuthorizationScope("write", "for write operations")};
	    return scopes;
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	      .securityReferences(
	        Arrays.asList(new SecurityReference("spring_oauth", scopes())))
	      .forPaths(PathSelectors.any())
	      .build();
	}

	private ApiInfoBuilder informationsApi() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("DGC");
		apiInfoBuilder.description("Diagnóstico de Gestão de conhecimento");
		apiInfoBuilder.contact(this.contact());

		return apiInfoBuilder;
	}

	private Contact contact() {

		return new Contact("João Salgado", "https://www.linkedin.com/in/joao-slgd/", "jgs1884@gmail.com");
	}
}
