package com.diagnostico.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.QuestionnaireStatus;

public interface BWQuestionnaireRepository extends JpaRepository<BWQuestionnaire, UUID> {

	public Optional<BWQuestionnaire> findByCompanyIdAndStatus(UUID companyId, QuestionnaireStatus status);
	
	public Optional<BWQuestionnaire> findByIdAndStatus(UUID id, QuestionnaireStatus status);

	public Optional<BWQuestionnaire> findById(UUID id);
		
	@Query("SELECT COUNT(DISTINCT company) "
		+ "FROM BWQuestionnaire "
		+ "WHERE status = :status")
	public Long countDistinctCompanyIdByStatus(@Param("status") QuestionnaireStatus status);

	public Long countByStatus(QuestionnaireStatus status);

	public Long countByCompanyCompanyProcessId(Long id);

	@Query("SELECT AVG(b.totalResult) "
			+ "FROM BWQuestionnaire b "
			+ "WHERE b.company.companyProcess.id = :id")
	public Double avgTotalResultByProcess(@Param("id") Long id);

}
