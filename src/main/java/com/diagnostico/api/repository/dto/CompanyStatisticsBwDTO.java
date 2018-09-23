package com.diagnostico.api.repository.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.diagnostico.api.model.BWPersonalSection;

public class CompanyStatisticsBwDTO {
	
	private UUID diagnosisId;
	private UUID diagnosisPersonalId;	
	private Integer totalPersonalResult;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private List<BWPersonalSection> bwPersonalSection;
	
	public CompanyStatisticsBwDTO(UUID diagnosisId, UUID diagnosisPersonalId, Integer totalPersonalResult,
			LocalDateTime startDate, LocalDateTime endDate) {
		super();
		this.diagnosisId = diagnosisId;
		this.diagnosisPersonalId = diagnosisPersonalId;
		this.totalPersonalResult = totalPersonalResult;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public UUID getDiagnosisId() {
		return diagnosisId;
	}
	
	public void setDiagnosisId(UUID diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	
	public UUID getDiagnosisPersonalId() {
		return diagnosisPersonalId;
	}
	
	public void setDiagnosisPersonalId(UUID diagnosisPersonalId) {
		this.diagnosisPersonalId = diagnosisPersonalId;
	}
	
	public Integer getTotalPersonalResult() {
		return totalPersonalResult;
	}
	
	public void setTotalPersonalResult(Integer totalPersonalResult) {
		this.totalPersonalResult = totalPersonalResult;
	}
	
	public LocalDateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	
	public LocalDateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	
	public List<BWPersonalSection> getBwPersonalSection() {
		return bwPersonalSection;
	}
	
	public void setBwPersonalSection(List<BWPersonalSection> bwPersonalSection) {
		this.bwPersonalSection = bwPersonalSection;
	}
	

	
	
	
	
	
}
