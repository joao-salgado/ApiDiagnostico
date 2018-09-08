package com.diagnostico.api.repository.projection;

import java.util.UUID;

public interface UserIdProjection {
	
	UserId getUser();
	
	interface UserId {
		  UUID getId();
	}
	
}
