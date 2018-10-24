package com.diagnostico.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.diagnostico.api.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

	public Optional<UserAccount> findByEmailAndActiveTrue(String email);
	
	public Optional<UserAccount> findById(UUID id);

	public List<UserAccount> findByCompanyIdAndUserGroupIdNot(UUID companyId, Long id);
	
	public Page<UserAccount> findByCompanyId(UUID id, Pageable pageable);

	public Long countByCompanyIdAndUserGroupIdNotAndActiveTrue(UUID companyId, Long id);

	public Long countByCompanyIdAndActiveTrue(UUID companyId);
	
}
