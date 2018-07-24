package com.diagnostico.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.diagnostico.api.config.property.DiagnosisApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(DiagnosisApiProperty.class)
public class DiagnosticoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosticoApiApplication.class, args);
	}
}
