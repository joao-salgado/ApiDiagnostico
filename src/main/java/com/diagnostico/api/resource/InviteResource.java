package com.diagnostico.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnostico.api.model.Invite;
import com.diagnostico.api.service.InviteService;

@RestController
@RequestMapping("/invitations")
public class InviteResource {
	
	@Autowired
	private InviteService inviteService;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody List<Invite> invitations) {
		inviteService.create(invitations);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

}
