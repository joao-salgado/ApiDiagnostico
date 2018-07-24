package com.diagnostico.api.event.listener;

import java.net.URI;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diagnostico.api.event.FeatureCreatedEvent;

public class FeatureCreatedListener implements ApplicationListener<FeatureCreatedEvent> {

	@Override
	public void onApplicationEvent(FeatureCreatedEvent featureCreatedEvent) {
		HttpServletResponse response = featureCreatedEvent.getResponse();
		UUID id = featureCreatedEvent.getId();
		addHeaderLocation(response, id);
	}

	private void addHeaderLocation(HttpServletResponse response, UUID id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}