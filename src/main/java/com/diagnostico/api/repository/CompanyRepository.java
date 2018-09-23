package com.diagnostico.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.Company;
import com.diagnostico.api.repository.company.CompanyRepositoryQuery;

public interface CompanyRepository extends JpaRepository<Company, UUID>, CompanyRepositoryQuery {

	public Optional<Company> findById(UUID id);
	
}
