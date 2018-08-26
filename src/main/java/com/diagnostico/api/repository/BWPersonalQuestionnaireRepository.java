package com.diagnostico.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.BWPersonalQuestionnaire;

public interface BWPersonalQuestionnaireRepository extends JpaRepository<BWPersonalQuestionnaire, UUID> {

}
