package com.diagnostico.api.repository.dto;

import java.util.List;

public class RearcherStatisticsBwDTO {

	private Long companyCount;
	
	private Long companyConcluedCount;
		
	private Long companyCanceledCount;
	
	private Long questionnairesOpenCount;
	
	private Long questionnairesConcluedCount;
	
	private Long questionnairesCanceledCount;
	
	private Long usersCount;
	
	private Long usersByCompany;
	
	private List<BWByProcess> byProcess;
	
	public RearcherStatisticsBwDTO() {}

	public RearcherStatisticsBwDTO(Long companyCount, Long companyConcluedCount, Long companyCanceledCount,
			Long questionnairesOpenCount, Long questionnairesConcluedCount, Long questionnairesCanceledCount,
			Long usersCount, Long usersByCompany, List<BWByProcess> byProcess) {
		super();
		this.companyCount = companyCount;
		this.companyConcluedCount = companyConcluedCount;
		this.companyCanceledCount = companyCanceledCount;
		this.questionnairesOpenCount = questionnairesOpenCount;
		this.questionnairesConcluedCount = questionnairesConcluedCount;
		this.questionnairesCanceledCount = questionnairesCanceledCount;
		this.usersCount = usersCount;
		this.usersByCompany = usersByCompany;
		this.byProcess = byProcess;
	}

	public Long getCompanyCount() {
		return companyCount;
	}

	public void setCompanyCount(Long companyCount) {
		this.companyCount = companyCount;
	}

	public Long getCompanyConcluedCount() {
		return companyConcluedCount;
	}

	public void setCompanyConcluedCount(Long companyConcluedCount) {
		this.companyConcluedCount = companyConcluedCount;
	}

	public Long getCompanyCanceledCount() {
		return companyCanceledCount;
	}

	public void setCompanyCanceledCount(Long companyCanceledCount) {
		this.companyCanceledCount = companyCanceledCount;
	}

	public Long getQuestionnairesOpenCount() {
		return questionnairesOpenCount;
	}

	public void setQuestionnairesOpenCount(Long questionnairesOpenCount) {
		this.questionnairesOpenCount = questionnairesOpenCount;
	}

	public Long getQuestionnairesConcluedCount() {
		return questionnairesConcluedCount;
	}

	public void setQuestionnairesConcluedCount(Long questionnairesConcluedCount) {
		this.questionnairesConcluedCount = questionnairesConcluedCount;
	}

	public Long getQuestionnairesCanceledCount() {
		return questionnairesCanceledCount;
	}

	public void setQuestionnairesCanceledCount(Long questionnairesCanceledCount) {
		this.questionnairesCanceledCount = questionnairesCanceledCount;
	}

	public Long getUsersCount() {
		return usersCount;
	}

	public void setUsersCount(Long usersCount) {
		this.usersCount = usersCount;
	}

	public Long getUsersByCompany() {
		return usersByCompany;
	}

	public void setUsersByCompany(Long usersByCompany) {
		this.usersByCompany = usersByCompany;
	}

	public List<BWByProcess> getByProcess() {
		return byProcess;
	}

	public void setByProcess(List<BWByProcess> byProcess) {
		this.byProcess = byProcess;
	}
	
}
