package com.diagnostico.api.event;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class FeatureCreatedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private UUID id;

	public FeatureCreatedEvent(Object source, HttpServletResponse response, UUID id) {
		super(source);
		this.response = response;
		this.id = id;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public UUID getId() {
		return id;
	}

}