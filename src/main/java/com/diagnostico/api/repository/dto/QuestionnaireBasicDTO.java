package com.diagnostico.api.repository.dto;

public class QuestionnaireBasicDTO {
	
	public QuestionnaireBasicDTO() {}
	
	public QuestionnaireBasicDTO(Object questionnaire, Long countUsers, Long countUsersWhoResponded) {
		super();
		this.questionnaire = questionnaire;
		this.countUsers = countUsers;
		this.countUsersWhoResponded = countUsersWhoResponded;
	}
	
	private Object questionnaire;
	
	private Long countUsers;
	
	private Long countUsersWhoResponded;

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

}
