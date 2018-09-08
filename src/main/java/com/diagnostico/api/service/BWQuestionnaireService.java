package com.diagnostico.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.diagnostico.api.config.property.DiagnosisApiProperty;
import com.diagnostico.api.email.EmailServiceImpl;
import com.diagnostico.api.exception.handler.QuestionnaireException;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.BWSection;
import com.diagnostico.api.model.Company;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.BWSectionRepository;
import com.diagnostico.api.repository.UserAccountRepository;

@Service
public class BWQuestionnaireService {

	@Autowired
	private BWQuestionnaireRepository questionnaireRepository;
	
	@Autowired
	private BWSectionRepository bwSectionRepository;
	
	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	public EmailServiceImpl emailService;
	
	@Autowired
	private DiagnosisApiProperty property;
	
	public BWQuestionnaire create(UUID companyId) {
		
		if(questionnaireRepository.findByCompanyIdAndStatus(companyId, QuestionnaireStatus.OPEN).isPresent()) {
			throw new QuestionnaireException("Já há um questionário de Bukowitz e Williams aberto.");
		}
		
		BWQuestionnaire bwq = new BWQuestionnaire();
		
		Company company = new Company();
		company.setId(companyId);
		
		bwq.setStatus(QuestionnaireStatus.OPEN);
		bwq.setTotalResult(0);
		bwq.setCompany(company);
		
		BWQuestionnaire bwqSaved = questionnaireRepository.save(bwq);
		bwSectionRepository.save(createGenericSections(bwqSaved));
		
		sendNotifications(companyId);
		
		return bwqSaved;
	}
	
	private List<BWSection> createGenericSections(BWQuestionnaire bwq) {
		
		List<BWSection> sections = new ArrayList<>();
		BWSection section;
		
		for(int i=1; i<8; i++) {
			section = new BWSection();
			section.setSection(i);
			section.setTotalResult(0);
			section.setBwQuestionnaire(bwq);
			sections.add(section);
		}
		
		return sections;
	}
	
	@Async
	private void sendNotifications(UUID companyId) {
		
		List<UserAccount> users = userRepository.findByCompanyIdAndUserGroupIdNot(companyId, 1l);
		
		for(UserAccount user : users) {
			Map<String, String> meta = new HashMap<String, String>();
			
			meta.put("linkDiagnosisBW", new String(property.getOriginPermitted() + "/#/diagnosticos/bw"));

			emailService.sendMessageUsingTemplateAttachment(user.getEmail(), "Diagnóstico aberto", "openBWQuestionnaire",
					null, meta);
		}
		
	}

}
