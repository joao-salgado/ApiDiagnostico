package com.diagnostico.api.resource;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.event.ResourceCreatedEvent;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.BWPersonalQuestionnaireRepository;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.UserAccountRepository;
import com.diagnostico.api.repository.dto.QuestionnaireBasicDTO;
import com.diagnostico.api.service.BWQuestionnaireService;

@RestController
@RequestMapping("/bw-questionnaires")
public class BWResource {
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private BWQuestionnaireService bwQuestionnaireService;
	
	@Autowired
	private BWQuestionnaireRepository bwQuestionnaireRepository;
	
	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private BWPersonalQuestionnaireRepository bwPersonalQuestionnaireRepository;
	
	@PostMapping("/companies/{companyId}")
	public ResponseEntity<BWQuestionnaire> create(@PathVariable UUID companyId, HttpServletResponse response) {
		
		BWQuestionnaire bwq = bwQuestionnaireService.create(companyId);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, bwq.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bwq);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		
		Optional<BWQuestionnaire> bwqOptional = bwQuestionnaireRepository.findById(id);
		
		if(bwqOptional.isPresent()) {
			BWQuestionnaire bwq = bwqOptional.get();
			bwq.setStatus(QuestionnaireStatus.CANCELED);
			bwQuestionnaireRepository.save(bwq);
		}
	}
	
	@GetMapping("/companies/{companyId}")
	public ResponseEntity<QuestionnaireBasicDTO> findByCompany(@PathVariable UUID companyId) {
		
		Optional<BWQuestionnaire> bwq = bwQuestionnaireRepository.findByCompanyIdAndStatus(companyId, QuestionnaireStatus.OPEN);
		
		if (!bwq.isPresent()) {
			return ResponseEntity.ok().build();
		}
		
		Long countUsers = userRepository.countByCompanyIdAndUserGroupIdNotAndActiveTrue(companyId, 1l);
		Long countUsersWhoResponded = bwPersonalQuestionnaireRepository.countByBwQuestionnaireId(bwq.get().getId());	
		
		return ResponseEntity.ok(new QuestionnaireBasicDTO(bwq.get(), countUsers, countUsersWhoResponded));
	}

}
