package com.diagnostico.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWPersonalSection;

public interface BWPersonalSectionRepository extends JpaRepository<BWPersonalSection, UUID> {

	List<BWPersonalSection> findByBwPersonalQuestionnaireId(UUID diagnosisPersonalId);

}
