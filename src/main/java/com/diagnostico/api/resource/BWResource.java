package com.diagnostico.api.resource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.event.ResourceCreatedEvent;
import com.diagnostico.api.model.BWPersonalQuestionnaire;
import com.diagnostico.api.model.BWQuestionnaire;
import com.diagnostico.api.model.QuestionnaireStatus;
import com.diagnostico.api.repository.BWPersonalQuestionnaireRepository;
import com.diagnostico.api.repository.BWQuestionnaireRepository;
import com.diagnostico.api.repository.UserAccountRepository;
import com.diagnostico.api.repository.dto.QuestionnaireBasicDTO;
import com.diagnostico.api.repository.projection.BWPersonalQuestionnaireProjection;
import com.diagnostico.api.repository.projection.UserIdProjection;
import com.diagnostico.api.service.BWPersonalQuestionnaireService;
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
	
	@Autowired
	private BWPersonalQuestionnaireService bwPersonalQuestionnaireService;
	
	@PostMapping("/companies/{companyId}")
	@PreAuthorize("hasAuthority('ROLE_DIAGNOSIS')")
	public ResponseEntity<BWQuestionnaire> create(@PathVariable UUID companyId, HttpServletResponse response) {
		
		BWQuestionnaire bwq = bwQuestionnaireService.create(companyId);
		
		publisher.publishEvent(new ResourceCreatedEvent(this, response, bwq.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bwq);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DIAGNOSIS')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable UUID id) {
		
		Optional<BWQuestionnaire> bwqOptional = bwQuestionnaireRepository.findById(id);
		
		if(bwqOptional.isPresent()) {
			
			List<BWPersonalQuestionnaire> lbwpq = bwPersonalQuestionnaireRepository.findByBwQuestionnaireIdAndStatus(id, QuestionnaireStatus.OPEN);
			
			lbwpq.forEach(q -> {
				q.setStatus(QuestionnaireStatus.CANCELED);
			});
			
			bwPersonalQuestionnaireRepository.save(lbwpq);
			
			BWQuestionnaire bwq = bwqOptional.get();
			bwq.setStatus(QuestionnaireStatus.CANCELED);
			bwQuestionnaireRepository.save(bwq);
		}
	}
	
	@PutMapping("/{id}/close")
	@PreAuthorize("hasAuthority('ROLE_DIAGNOSIS')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void closeDiagnosis(@PathVariable UUID id) {
		
		Optional<BWQuestionnaire> bwqOptional = bwQuestionnaireRepository.findById(id);
		
		if(bwqOptional.isPresent()) {
			
			List<BWPersonalQuestionnaire> lbwpq = bwPersonalQuestionnaireRepository.findByBwQuestionnaireIdAndStatus(id, QuestionnaireStatus.OPEN);
			
			lbwpq.forEach(q -> {
				q.setStatus(QuestionnaireStatus.CANCELED);
			});
			
			bwPersonalQuestionnaireRepository.save(lbwpq);
			
			BWQuestionnaire bwq = bwqOptional.get();
			bwq.setStatus(QuestionnaireStatus.CLOSED);
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
		Long countUsersWhoResponded = bwPersonalQuestionnaireRepository.countByStatusAndBwQuestionnaireId(QuestionnaireStatus.CLOSED, bwq.get().getId());
		List<UserIdProjection> usersWhoResponded = bwPersonalQuestionnaireRepository.findByBwQuestionnaireId(bwq.get().getId());
		
		return ResponseEntity.ok(new QuestionnaireBasicDTO(bwq.get(), countUsers, countUsersWhoResponded, usersWhoResponded));
	}
	
	@PostMapping("/{diagnosisId}/personal")
	public ResponseEntity<BWPersonalQuestionnaire> savePersonalDiagnosis(@PathVariable UUID diagnosisId, @Valid @RequestBody BWPersonalQuestionnaire questionnaire, HttpServletResponse response) {
		BWPersonalQuestionnaire pbwq = bwPersonalQuestionnaireService.create(diagnosisId, questionnaire);
		return ResponseEntity.status(HttpStatus.CREATED).body(pbwq);
	}
	
	@PutMapping("/{diagnosisId}/personal/{personalDiagnosisId}")
	@ResponseStatus(HttpStatus.OK)
	public void updatePersonalDiagnosis(@PathVariable UUID diagnosisId, @PathVariable UUID personalDiagnosisId, @Valid @RequestBody BWPersonalQuestionnaire questionnaire, HttpServletResponse response) {
		bwPersonalQuestionnaireService.update(diagnosisId, questionnaire);
	}
	
	@GetMapping("/personal/{userId}")
	public BWPersonalQuestionnaireProjection updatePersonalDiagnosis(@PathVariable UUID userId, HttpServletResponse response) {
		return bwPersonalQuestionnaireRepository.findByUserIdAndStatus(userId, QuestionnaireStatus.OPEN);
	}

}
