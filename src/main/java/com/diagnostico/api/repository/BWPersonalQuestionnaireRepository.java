package com.diagnostico.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWPersonalQuestionnaire;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.projection.BWPersonalQuestionnaireProjection;
import com.diagnostico.api.repository.projection.UserIdProjection;

public interface BWPersonalQuestionnaireRepository extends JpaRepository<BWPersonalQuestionnaire, UUID> {

	List<UserIdProjection> findByBwQuestionnaireId(UUID id);

	Long countByStatusAndBwQuestionnaireId(QuestionnaireStatus closed, UUID id);

	BWPersonalQuestionnaireProjection findByUserIdAndStatus(UUID userId, QuestionnaireStatus open);

	List<BWPersonalQuestionnaire> findByBwQuestionnaireIdAndStatus(UUID id, QuestionnaireStatus open);
	
	

}
