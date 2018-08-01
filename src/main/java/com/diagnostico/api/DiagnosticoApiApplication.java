package com.diagnostico.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.diagnostico.api.config.property.DiagnosisApiProperty;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

@SpringBootApplication
@EnableConfigurationProperties(DiagnosisApiProperty.class)
public class DiagnosticoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticoApiApplication.class, args);
	}
	
	@Bean
	public Module hibernate4Module() {
	    return new Hibernate4Module();
	}
}
