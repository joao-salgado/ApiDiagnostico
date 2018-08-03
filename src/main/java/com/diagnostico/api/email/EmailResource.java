package com.diagnostico.api.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
public class EmailResource {

	@Autowired
	public EmailServiceImpl emailService;
	
	@GetMapping
	public ResponseEntity<?> statusEmail() {
		emailService.sendSimpleMessage("jgs1884@outlook.com", "Email de Confiramção de funcionamento de envio de email", "Parabéns por está funcionando o envio de email do gmail");
		return ResponseEntity.ok().build();
	}
	
}
