package com.security.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPrincipal implements UserDetails{

	private Users user;
	
	
	
	public UserPrincipal(Users user) {
		this.user = user;
	}

	public UserPrincipal() {
		super();
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton( new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

}
