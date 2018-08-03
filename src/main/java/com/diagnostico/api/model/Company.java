package com.diagnostico.api.model;

import java.io.Serializable;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@NotEmpty(message = "Nome é um campo obrigatório")
	private String name;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private JsonNode meta;
	
	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;
	
	@NotNull(message = "O processo é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_process_id")
	private CompanyProcess companyProcess;
	
	@Valid
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<UserAccount> userAccount;
	
	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private List<Invite> invitations;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JsonNode getMeta() {
		return meta;
	}

	public void setMeta(JsonNode meta) {
		this.meta = meta;
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

	public CompanyProcess getCompanyProcess() {
		return companyProcess;
	}

	public void setCompanyProcess(CompanyProcess companyProcess) {
		this.companyProcess = companyProcess;
	}

	public List<UserAccount> getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(List<UserAccount> userAccount) {
		this.userAccount = userAccount;
	}
	
	public List<Invite> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<Invite> invitations) {
		this.invitations = invitations;
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
