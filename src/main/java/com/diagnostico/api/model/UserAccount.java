package com.diagnostico.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.diagnostico.api.model.json.JsonBinaryType;
import com.diagnostico.api.validation.PHONE_NUMBER;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.JsonNode;

@TypeDefs({ @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private UUID id;
	
	@NotEmpty(message = "Nome do usuário é um campo obrigatório")
	private String name;
	
	@NotEmpty(message = "Email é um campo obrigatório")
	@Email(message = "Email deve ser válido")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@PHONE_NUMBER
	private String phone;
	
	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;
	
	private Boolean active;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;

	private Date birthdate;
	
	@Column(name="start_work")
	private LocalDateTime startWork;
	
	@NotNull(message = "O tipo de usuário é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_type_id")
	private UserType userType;
	
	@NotNull(message = "Grupo do usuário é um campo obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_group_id")
	private UserGroup userGroup;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "json")
	private JsonNode meta;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<BWPersonalQuestionnaire> bwPersonalQuestionnaires;
	
	@PrePersist
	public void setAttributePrePersist() {
		this.created = LocalDateTime.now();
		this.active = true;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public LocalDateTime getStartWork() {
		return startWork;
	}

	public void setStartWork(LocalDateTime startWork) {
		this.startWork = startWork;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public JsonNode getMeta() {
		return meta;
	}

	public void setMeta(JsonNode meta) {
		this.meta = meta;
	}

	public List<BWPersonalQuestionnaire> getBwPersonalQuestionnaires() {
		return bwPersonalQuestionnaires;
	}

	public void setBwPersonalQuestionnaires(List<BWPersonalQuestionnaire> bwPersonalQuestionnaires) {
		this.bwPersonalQuestionnaires = bwPersonalQuestionnaires;
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
		UserAccount other = (UserAccount) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
