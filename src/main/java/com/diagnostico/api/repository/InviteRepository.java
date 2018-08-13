package com.diagnostico.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {
	
	Optional<Invite> findByCode(String code);

}
