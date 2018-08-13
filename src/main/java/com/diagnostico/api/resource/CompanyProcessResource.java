package com.diagnostico.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.model.CompanyProcess;
import com.diagnostico.api.repository.CompanyProcessRepository;

@RestController
@RequestMapping("/company-processes")
public class CompanyProcessResource {

	@Autowired
	private CompanyProcessRepository processRepository;
	
	@GetMapping
	public List<CompanyProcess> findAll() {
		return processRepository.findAll();
	}
	
}
