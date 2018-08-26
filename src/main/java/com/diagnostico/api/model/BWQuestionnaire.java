package com.diagnostico.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "bw_questionnaire")
public class BWQuestionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "total_result")
	private BigDecimal totalResult;
	
	@Enumerated(EnumType.STRING)
	private QuestionnaireStatus status;
	
	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private JsonNode meta;
	
	@NotNull(message = "Empresa é obrigatória")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@OneToMany(mappedBy = "bwQuestionnaire", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<BWSection> bwSections;
	
	@OneToMany(mappedBy = "bwQuestionnaire", fetch = FetchType.LAZY)
	private List<BWPersonalQuestionnaire> bwPersonalQuestionnaire;
	
	@PrePersist
	public void setAttributePrePersist() {
		this.created = LocalDateTime.now();
	}

	@PreUpdate
	public void setAttributePreUpdate() {
		this.modified = LocalDateTime.now();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(BigDecimal totalResult) {
		this.totalResult = totalResult;
	}

	public QuestionnaireStatus getStatus() {
		return status;
	}

	public void setStatus(QuestionnaireStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public JsonNode getMeta() {
		return meta;
	}

	public void setMeta(JsonNode meta) {
		this.meta = meta;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<BWSection> getBwSections() {
		return bwSections;
	}

	public void setBwSections(List<BWSection> bwSections) {
		this.bwSections = bwSections;
	}

	public List<BWPersonalQuestionnaire> getBwPersonalQuestionnaire() {
		return bwPersonalQuestionnaire;
	}

	public void setBwPersonalQuestionnaire(List<BWPersonalQuestionnaire> bwPersonalQuestionnaire) {
		this.bwPersonalQuestionnaire = bwPersonalQuestionnaire;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BWQuestionnaire other = (BWQuestionnaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
