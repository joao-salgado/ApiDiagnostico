package com.diagnostico.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diagnostico.api.exception.handler.EmailException;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.UserAccountRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userRepository;
	
	public UserAccount create(UserAccount user) {
		
		verifyExistingUserByEmail(user.getEmail());
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	private void verifyExistingUserByEmail(String email) {
		Optional<UserAccount> user = userRepository.findByEmail(email);
		if (user.isPresent()) {
			throw new EmailException("Email já existente");
		}
	}
	
	@SuppressWarnings("unused")
	private UserAccount verifyExistingUserById(UUID id) {
		Optional<UserAccount> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return user.get();
	}
	
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
