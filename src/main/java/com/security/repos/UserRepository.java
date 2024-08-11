package com.security.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.models.Users;

public interface UserRepository extends JpaRepository<Users, Integer>{

	Users findByUsername(String username);
	
	
	
}
