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
import com.diagnostico.api.model.Invite;
import com.diagnostico.api.model.InviteSituation;
import com.diagnostico.api.repository.InviteRepository;

@Service
public class InviteService {

	@Autowired
	private InviteRepository inviteRepository;
	
	@Autowired
	public EmailServiceImpl emailService;
	
	@Autowired
	private DiagnosisApiProperty property;
	
	public List<Invite> create(List<Invite> invitations) {
		
		List<Invite> in = new ArrayList<Invite>();
		
		for (Invite invite : invitations) {
			if(!invite.getEmail().isEmpty()) {
				invite.setCode(UUID.randomUUID().toString());
				invite.setSituation(InviteSituation.SEND);
				in.add(invite);
			}
		}
		
		List<Invite> savedInvitations = inviteRepository.save(in);
		sendInvite(savedInvitations);
		
		return savedInvitations;
		
	}
	
	@Async
	private void sendInvite(List<Invite> invitations) {
		
		for(Invite invite : invitations) {
			Map<String, String> meta = new HashMap<String, String>();
			
			meta.put("linkCreateUserAccount", new String(property.getOriginPermitted() + "/#/cadastro-usuario?code=" + invite.getCode()));

			emailService.sendMessageUsingTemplateAttachment(invite.getEmail(), "Criação de conta", "createUserAccount",
					null, meta);
		}
		
	}
	
}
