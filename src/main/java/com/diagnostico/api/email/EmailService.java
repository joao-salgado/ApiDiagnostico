package com.diagnostico.api.email;

import java.util.Map;

import org.springframework.scheduling.annotation.Async;

public interface EmailService {

	@Async
	void sendSimpleMessage(String to, String subject, String text);

	@Async
	void sendMessageUsingTemplateAttachment(String to, String subject, String typeEmail, String urlAttachment, Map<String,String> meta);
	
}
