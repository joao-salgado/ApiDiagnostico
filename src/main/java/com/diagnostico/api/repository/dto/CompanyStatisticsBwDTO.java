package com.diagnostico.api.repository.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.diagnostico.api.model.BWPersonalSection;

public class CompanyStatisticsBwDTO {
	
	private UUID id;	
	private Integer totalResult;
	private LocalDateTime created;
	private List<BWPersonalSection> bwPersonalSection;
	
	public CompanyStatisticsBwDTO(UUID id, Integer totalResult, LocalDateTime created/*,
			List<BWPersonalSection> bwPersonalSection*/) {
		super();
		this.id = id;
		this.totalResult = totalResult;
		this.created = created;
		// this.bwPersonalSection = bwPersonalSection;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(Integer totalResult) {
		this.totalResult = totalResult;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public List<BWPersonalSection> getBwPersonalSection() {
		return bwPersonalSection;
	}

	public void setBwPersonalSection(List<BWPersonalSection> bwPersonalSection) {
		this.bwPersonalSection = bwPersonalSection;
	}
	
	
	
	
}
