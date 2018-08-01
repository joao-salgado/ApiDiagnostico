package com.diagnostico.api.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.event.ResourceCreatedEvent;
import com.diagnostico.api.model.Company;
import com.diagnostico.api.repository.CompanyRepository;
import com.diagnostico.api.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@GetMapping
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Company> findById(@PathVariable UUID id) {
		
		Optional<Company> company = companyRepository.findById(id);
		if (!company.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(company.get());
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Company company, HttpServletRequest request,
			HttpServletResponse response) {
		
		Company companySaved = companyService.create(company);
		publisher.publishEvent(new ResourceCreatedEvent(this, response, companySaved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(companySaved);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Company> update(@PathVariable UUID id, @Valid @RequestBody Company company,
			OAuth2Authentication auth) {

		Company companySaved = companyService.update(id, company);
		return ResponseEntity.ok(companySaved);
		
	}
	

}
