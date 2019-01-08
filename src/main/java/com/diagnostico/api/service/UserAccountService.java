package com.diagnostico.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.diagnostico.api.exception.handler.EmailException;
import com.diagnostico.api.exception.handler.TokenCodeException;
import com.diagnostico.api.model.Company;
import com.diagnostico.api.model.Invite;
import com.diagnostico.api.model.InviteSituation;
import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.model.UserType;
import com.diagnostico.api.repository.InviteRepository;
import com.diagnostico.api.repository.UserAccountRepository;
import com.diagnostico.api.repository.UserTypeRepository;

@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepository userRepository;
	
	@Autowired
	private UserTypeRepository userTypeRepository;
	
	@Autowired
	private InviteRepository inviteRepository;
	
	public UserAccount createByCompany(UserAccount user) {
		verifyExistingUserByEmail(user.getEmail());
		
		verifyIfNewUserType(user);
		
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}

	private void verifyIfNewUserType(UserAccount user) {
		/**
		 * VERIFICA SE O TIPO DE PAPEL SELECIONADO FOI DO TIPO "OUTRO"
		 */
		if(user.getUserType().getId() == 13) {
			String userType = user.getMeta().get("other_user_type").asText();
			if(!userType.isEmpty()) {
				UserType newUserType = userTypeRepository.save(new UserType(userType));
				user.getUserType().setId(newUserType.getId());
			}
		}
	}
	
	public UserAccount createByCode(UserAccount user) {
		
		verifyExistingUserByEmail(user.getEmail());
		
		verifyIfNewUserType(user);
		
		Invite invite = verifyCode(user.getMeta().get("companyCode").asText());
		user.setCompany(new Company());	
		user.getCompany().setId(invite.getCompany().getId());
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		
		invite.setSituation(InviteSituation.ACCEPTED);
		inviteRepository.save(invite);
		
		return userRepository.save(user);
	}
	
	public UserAccount update(UUID id, UserAccount user) {
		UserAccount userSaved = verifyExistingUserById(id);
		String[] ignoredProperties = { "id", "password" };
		BeanUtils.copyProperties(user, userSaved, ignoredProperties);
		
		return userRepository.save(userSaved);
	}
	
	public Invite verifyCode(String code) {
		Optional<Invite> invite =  inviteRepository.findByCode(code);
		
		if(!(invite.isPresent() && invite.get().getSituation().equals(InviteSituation.SEND))) {
			throw new TokenCodeException("Código do convite inválido ou já utilizado");
		}
		
		return invite.get();
	}
	
	public void verifyExistingUserByEmail(String email) {
		Optional<UserAccount> user = userRepository.findByEmailAndActiveTrue(email);
		if (user.isPresent()) {
			throw new EmailException("Email já existente");
		}
	}
	
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
