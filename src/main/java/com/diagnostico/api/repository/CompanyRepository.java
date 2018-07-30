package com.diagnostico.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.Company;

public interface CompanyRepository extends JpaRepository<Company, UUID> {

}
