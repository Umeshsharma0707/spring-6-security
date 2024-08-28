package com.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.security.models.UserPrincipal;
import com.security.models.Users;
import com.security.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
		
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	private JWTService jwtService;

	public String verify(Users user) {
	   
		System.out.println("user service");
		
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
	    );
	    
	    
	    if (authentication.isAuthenticated()) {
	      
	        return jwtService.generateToken(user.getUsername());
	    } else {
	      
	        return "Authentication failed!";
	    }
	}

}
