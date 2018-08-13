package com.diagnostico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.CompanyProcess;

public interface CompanyProcessRepository extends JpaRepository<CompanyProcess, Long> {

}
