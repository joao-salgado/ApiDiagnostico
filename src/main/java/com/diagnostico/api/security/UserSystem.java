package com.diagnostico.api.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.diagnostico.api.model.UserAccount;

public class UserSystem extends User {

	private static final long serialVersionUID = 1L;

	private UserAccount user;

	public UserSystem(UserAccount user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getEmail(), user.getPassword(), authorities);
		this.user = user;
	}

	public UserAccount getUser() {
		return user;
	}
	
}
