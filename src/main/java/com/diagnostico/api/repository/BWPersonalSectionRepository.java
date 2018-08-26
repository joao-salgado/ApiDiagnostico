package com.diagnostico.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWPersonalSection;

public interface BWPersonalSectionRepository extends JpaRepository<BWPersonalSection, UUID> {

}
