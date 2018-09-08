package com.diagnostico.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWPersonalQuestionnaire;
import com.diagnostico.api.repository.projection.UserIdProjection;

public interface BWPersonalQuestionnaireRepository extends JpaRepository<BWPersonalQuestionnaire, UUID> {

	Long countByBwQuestionnaireId(UUID id);

	List<UserIdProjection> findByBwQuestionnaireId(UUID id);

}
