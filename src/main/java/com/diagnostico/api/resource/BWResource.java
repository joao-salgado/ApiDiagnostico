package com.diagnostico.api.resource;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.event.ResourceCreatedEvent;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.service.BWQuestionnaireService;

@RestController
@RequestMapping("/bw-questionnaires")
public class BWResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private BWQuestionnaireService bwQuestionnaireService;
	
	@PostMapping("/company/{companyId}")
	public ResponseEntity<BWQuestionnaire> create(@PathVariable UUID companyId, HttpServletResponse response) {
		
		BWQuestionnaire bwq = bwQuestionnaireService.create(companyId);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, bwq.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bwq);
	}

}
