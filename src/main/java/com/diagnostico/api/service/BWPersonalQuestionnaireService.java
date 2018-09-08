package com.diagnostico.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diagnostico.api.exception.handler.QuestionnaireException;
import com.diagnostico.api.model.BWPersonalQuestionnaire;
import com.diagnostico.api.model.BWPersonalSection;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.BWSection;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.BWPersonalQuestionnaireRepository;
import com.diagnostico.api.repository.BWPersonalSectionRepository;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.BWSectionRepository;

@Service
public class BWPersonalQuestionnaireService {
	
	@Autowired
	private BWQuestionnaireRepository questionnaireRepository;
	
	@Autowired
	private BWSectionRepository bwSectionRepository;
	
	@Autowired
	private BWPersonalQuestionnaireRepository questionnairePersonalRepository;
	
	@Autowired
	private BWPersonalSectionRepository bwSectionPersonalRepository;
	
	public BWPersonalQuestionnaire create(UUID questionnaireId, BWPersonalQuestionnaire questionnaire) {
		
		Optional<BWQuestionnaire> bwqOptional = questionnaireRepository.findByIdAndStatus(questionnaireId, QuestionnaireStatus.OPEN);
		
		if(!bwqOptional.isPresent()) {
			throw new QuestionnaireException("Questionário não existe ou já está fechado.");
		}
		
		if(questionnaire.getBwPersonalSection().size() != 7) {
			throw new QuestionnaireException("Devem haver 7 seções no questionário.");
		}
		
		BWPersonalQuestionnaire bwpq = questionnairePersonalRepository.save(questionnaire);
		
		List<BWPersonalSection> bwpsq = questionnaire.getBwPersonalSection();
		bwpsq.forEach(section -> {
			section.setBwPersonalQuestionnaire(new BWPersonalQuestionnaire());
			section.getBwPersonalQuestionnaire().setId(bwpq.getId());
		});
		bwSectionPersonalRepository.save(bwpsq);
		
		BWQuestionnaire bwq = bwqOptional.get();
		bwq.setTotalResult(bwq.getTotalResult() + bwpq.getTotalResult());
		questionnaireRepository.save(bwq);
		
		List<BWSection> bwSections = bwSectionRepository.findByBwQuestionnaireId(bwq.getId());
		int index = 0;
		for(BWSection section : bwSections) {
			section.setTotalResult(section.getTotalResult() + bwpsq.get(index).getTotalResult());
			index++;
		}
		bwSectionRepository.save(bwSections);
		
		return bwpq;
	}

}
