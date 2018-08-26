package com.diagnostico.api.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diagnostico.api.exception.handler.QuestionnaireException;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.BWSection;
import com.diagnostico.api.model.Company;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.BWSectionRepository;

@Service
public class BWQuestionnaireService {

	@Autowired
	private BWQuestionnaireRepository questionnaireRepository;
	
	@Autowired
	private BWSectionRepository bwSectionRepository;
	
	public BWQuestionnaire create(UUID companyId) {
		
		if(questionnaireRepository.findByCompanyIdAndStatus(companyId, QuestionnaireStatus.OPEN).isPresent()) {
			throw new QuestionnaireException("Já há um questionário de Bukowitz e Williams aberto.");
		}
		
		BWQuestionnaire bwq = new BWQuestionnaire();
		
		Company company = new Company();
		company.setId(companyId);
		
		bwq.setStatus(QuestionnaireStatus.OPEN);
		bwq.setTotalResult(new BigDecimal(0));
		bwq.setCompany(company);
		
		BWQuestionnaire bwqSaved = questionnaireRepository.save(bwq);
		bwSectionRepository.save(createGenericSections(bwqSaved));
		
		return bwqSaved;
	}
	
	private List<BWSection> createGenericSections(BWQuestionnaire bwq) {
		
		List<BWSection> sections = new ArrayList<>();
		BWSection section;
		
		for(int i=1; i<8; i++) {
			section = new BWSection();
			section.setSection(i);
			section.setTotalResult(new BigDecimal(0));
			section.setBwQuestionnaire(bwq);
			sections.add(section);
		}
		
		return sections;
	}
	
}
