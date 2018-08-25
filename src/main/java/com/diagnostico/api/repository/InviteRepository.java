package com.diagnostico.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
	
	public Optional<Invite> findByCode(String code);
	
	public Page<Invite> findByCompanyId(UUID id, Pageable pageable);

}
