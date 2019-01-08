package com.diagnostico.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {


}
