package com.diagnostico.api.repository.projection;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;

public interface BWPersonalQuestionnaireProjection {

	UUID getId();
	
	List<BWPersonalSectionInterface> getBwPersonalSection();
	
	interface BWPersonalSectionInterface {
		
		UUID getId();
		JsonNode getMeta();
		Integer getSection();
		
	}
	
}
