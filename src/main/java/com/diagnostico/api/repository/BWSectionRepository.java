package com.diagnostico.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWSection;

public interface BWSectionRepository extends JpaRepository<BWSection, UUID> {

	List<BWSection> findByBwQuestionnaireId(UUID id);

}
