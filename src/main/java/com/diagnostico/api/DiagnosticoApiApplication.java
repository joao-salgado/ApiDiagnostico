package com.diagnostico.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import com.diagnostico.api.config.property.DiagnosisApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DiagnosisApiProperty.class)
public class DiagnosticoApiApplication {
	
	private static ApplicationContext APPLICATION_CONTEXT;

	public static void main(String[] args) {
		APPLICATION_CONTEXT = SpringApplication.run(DiagnosticoApiApplication.class, args);
	}
	
	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}
	
	@Bean
	public Module hibernate4Module() {
	    return new Hibernate4Module();
	}
}
