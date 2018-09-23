package com.diagnostico.api.repository.filter;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class CompanyStatisticsFilter {

	private Long role;
	
	private UUID company;
	
	private String diagnosis;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate start;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate end;

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public UUID getCompany() {
		return company;
	}

	public void setCompany(UUID company) {
		this.company = company;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public LocalDate getStart() {
		return start;
	}

	public void setStart(LocalDate start) {
		this.start = start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public void setEnd(LocalDate end) {
		this.end = end;
	}
	
}
