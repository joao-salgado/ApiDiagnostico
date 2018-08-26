package com.diagnostico.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "bw_section")
public class BWSection implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@NotNull(message="O número da seção é obrigatória")
	private Integer section;
	
	@NotNull(message="O resultado da seção é obrigatório")
	@Column(name = "total_result")
	private BigDecimal totalResult;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private JsonNode meta;
	
	@NotNull(message = "O questionário é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bw_questionnaire_id")
	private BWQuestionnaire bwQuestionnaire;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getSection() {
		return section;
	}

	public void setSection(Integer section) {
		this.section = section;
	}

	public BigDecimal getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(BigDecimal totalResult) {
		this.totalResult = totalResult;
	}

	public JsonNode getMeta() {
		return meta;
	}

	public void setMeta(JsonNode meta) {
		this.meta = meta;
	}

	public BWQuestionnaire getBwQuestionnaire() {
		return bwQuestionnaire;
	}

	public void setBwQuestionnaire(BWQuestionnaire bwQuestionnaire) {
		this.bwQuestionnaire = bwQuestionnaire;
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
		BWSection other = (BWSection) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
