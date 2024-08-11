package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.service.MyUserDetailsServive;

import jakarta.websocket.Session;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
		@Autowired
		private MyUserDetailsServive userDetailsServive;
			
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}
		
		@Bean
		public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
			http.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(request -> request.requestMatchers("/home").permitAll().anyRequest().authenticated())
			.formLogin(Customizer.withDefaults())
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			
			return http.build();
		}
		
		@Bean
		public AuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
			daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
			daoAuthenticationProvider.setUserDetailsService(userDetailsServive);
			return daoAuthenticationProvider;
		}
}
