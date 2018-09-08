package com.diagnostico.api.repository.dto;

import java.util.List;

import com.diagnostico.api.repository.projection.UserIdProjection;

public class QuestionnaireBasicDTO {
	
	public QuestionnaireBasicDTO() {}
	
	public QuestionnaireBasicDTO(Object questionnaire, Long countUsers, Long countUsersWhoResponded, List<UserIdProjection> usersWhoResponded) {
		super();
		setQuestionnaire(questionnaire);
		setCountUsers(countUsers);
		setCountUsersWhoResponded(countUsersWhoResponded);
		setUsersWhoResponded(usersWhoResponded);
	}
	
	private Object questionnaire;
	
	private Long countUsers;
	
	private Long countUsersWhoResponded;
	
	private List<UserIdProjection> usersWhoResponded;

	public Object getQuestionnaire() {
		return questionnaire;
	}

	public void setQuestionnaire(Object questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Long getCountUsers() {
		return countUsers;
	}

	public void setCountUsers(Long countUsers) {
		this.countUsers = countUsers;
	}

	public Long getCountUsersWhoResponded() {
		return countUsersWhoResponded;
	}

	public void setCountUsersWhoResponded(Long countUsersWhoResponded) {
		this.countUsersWhoResponded = countUsersWhoResponded;
	}

	public List<UserIdProjection> getUsersWhoResponded() {
		return usersWhoResponded;
	}

	public void setUsersWhoResponded(List<UserIdProjection> usersWhoResponded) {
		this.usersWhoResponded = usersWhoResponded;
	}

}
