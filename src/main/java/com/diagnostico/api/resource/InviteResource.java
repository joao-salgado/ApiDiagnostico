package com.diagnostico.api.resource;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.model.Invite;
import com.diagnostico.api.repository.InviteRepository;
import com.diagnostico.api.service.InviteService;

@RestController
@RequestMapping("/invitations")
public class InviteResource {
	
	@Autowired
	private InviteService inviteService;
	
	@Autowired
	private InviteRepository inviteRepository;
	
	@PostMapping
	public ResponseEntity<Invite> create(@Valid @RequestBody List<Invite> invitations) {
		inviteService.create(invitations);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/companies/{id}")
	public Page<Invite> findAll(@PathVariable UUID id, Pageable pageable) {
		return inviteRepository.findByCompanyId(id, pageable);
	}

}
