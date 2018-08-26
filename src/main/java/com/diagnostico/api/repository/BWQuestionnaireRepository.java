package com.diagnostico.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.QuestionnaireStatus;

public interface BWQuestionnaireRepository extends JpaRepository<BWQuestionnaire, UUID> {

	public Optional<BWQuestionnaire> findByCompanyIdAndStatus(UUID companyId, QuestionnaireStatus status);

}
