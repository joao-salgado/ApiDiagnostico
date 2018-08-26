package com.diagnostico.api.email;

import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Autowired
	private TemplateEngine thymeleaf;
	
	@Override
	public void sendSimpleMessage(String to, String subject, String text) {

		try {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("diagnosticoappcontato@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
		}
	}

	@Override
	@Async
	public void sendMessageUsingTemplateAttachment(String to, String subject, String typeEmail, String urlAttachment,
			Map<String,String>  meta) {

		try {

			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");

			Context context = new Context(new Locale("pt", "BR"));
			String content = "";
			
			if (meta.containsKey("linkCreateUserAccount")) {
				context.setVariable("linkCreateUserAccount", meta.get("linkCreateUserAccount"));
			}
			
			if (meta.containsKey("linkDiagnosisBW")) {
				context.setVariable("linkDiagnosisBW", meta.get("linkDiagnosisBW"));
			}

			switch (typeEmail) {
				case "createUserAccount":
					content = thymeleaf.process("email/createUserAccount", context);
					break;
				case "openBWQuestionnaire":
					content = thymeleaf.process("email/openBWQuestionnaire", context);
					break;
			}

			context.setVariable("conteudo", content);
			
			String email = thymeleaf.process("email/standartLayout", context);

			Multipart multiPart = new MimeMultipart("alternative");
			if (urlAttachment != null) {
				MimeBodyPart filePart = new MimeBodyPart();
				DataSource source = new FileDataSource(urlAttachment);
				filePart.setDataHandler(new DataHandler(source));
				filePart.setFileName(source.getName());
				multiPart.addBodyPart(filePart);
				mimeMessage.setContent(multiPart);
				mimeMessage.setFrom("diagnosticoappcontato@gmail.com");
			}
			
			helper.setFrom("diagnosticoappcontato@gmail.com");
			helper.setText(email, true);
			//helper.addInline("logo", new ClassPathResource("static/img/logobeta.png"));
			helper.setTo(to);
			helper.setSubject(subject);

			emailSender.send(mimeMessage);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	 @Async
	  public void sendEmail(SimpleMailMessage email) {
	    emailSender.send(email);
	  }
	
}
