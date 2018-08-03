package com.diagnostico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.Invite;

public interface InviteRepository extends JpaRepository<Invite, Long> {

}
