package com.diagnostico.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diagnostico.api.model.BWSection;

public interface BWSectionRepository extends JpaRepository<BWSection, UUID> {

	public List<BWSection> findByBwQuestionnaireId(UUID id);

	@Query("SELECT AVG(s.totalResult) "
			+ "FROM BWSection s "
			+ "WHERE s.bwQuestionnaire.company.companyProcess.id = :id "
			+ "AND s.section = :section")
	public Double avgTotalBySectionByProcess(@Param("section") Integer section, @Param("id") Long id);

}
