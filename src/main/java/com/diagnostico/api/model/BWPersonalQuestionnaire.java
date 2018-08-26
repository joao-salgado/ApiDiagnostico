package com.diagnostico.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "bw_personal_questionnaire")
public class BWPersonalQuestionnaire implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "total_result")
	private BigDecimal totalResult;
	
	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private JsonNode meta;
	
	@NotNull(message = "Usuário é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_account_id")
	private UserAccount user;
	
	@NotNull(message = "Questionário principal é obrigatório")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bw_questionnaire_id")
	private BWQuestionnaire bwQuestionnaire;
	
	@OneToMany(mappedBy = "bwPersonalQuestionnaire", fetch = FetchType.EAGER)
	private List<BWPersonalSection> bwPersonalSection;
	
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

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	public BWQuestionnaire getBwQuestionnaire() {
		return bwQuestionnaire;
	}

	public void setBwQuestionnaire(BWQuestionnaire bwQuestionnaire) {
		this.bwQuestionnaire = bwQuestionnaire;
	}

	public List<BWPersonalSection> getBwPersonalSection() {
		return bwPersonalSection;
	}

	public void setBwPersonalSection(List<BWPersonalSection> bwPersonalSection) {
		this.bwPersonalSection = bwPersonalSection;
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
		BWPersonalQuestionnaire other = (BWPersonalQuestionnaire) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
