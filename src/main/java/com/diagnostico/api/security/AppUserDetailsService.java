package com.diagnostico.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.diagnostico.api.model.UserAccount;
import com.diagnostico.api.repository.UserAccountRepository;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserAccount> user = userAccountRepository.findByEmail(email);
		UserAccount userAccount = user.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new UserSystem(userAccount, getPermissions(userAccount));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserAccount user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getUserGroup().getListPermission()
				.forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getRole().toUpperCase())));
		return authorities;
	}
	
}
