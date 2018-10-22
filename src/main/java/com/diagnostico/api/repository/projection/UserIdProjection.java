package com.diagnostico.api.repository.projection;

import java.util.UUID;

import com.diagnostico.api.model.QuestionnaireStatus;

public interface UserIdProjection {
	
	UserId getUser();
	
	QuestionnaireStatus getStatus();
	
	interface UserId {
		UUID getId();
	}
	
}
