package com.security.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.models.Users;
import com.security.repos.UserRepository;
import com.security.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	
	@Autowired
	 private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	private List<Student> students = new ArrayList<Student>(List.of(
			new Student(10, "umesh", "50"),
			new Student(15,"java","50")
			));
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String index() {
		return "this is a index page";
		
	}
	
	@GetMapping("/hello")
	public String hello() {
		String pass = passwordEncoder.encode("u123");
		System.out.println(pass);
		return "hello bro";	
	}
	@GetMapping("/students")
	public List<Student> students() {
		return students;
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken geCsrfToken(HttpServletRequest request) {
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	
	
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		students.add(student);
		return student;
	}
	@GetMapping("/users")
	public List<Users> getUsers() {
		List<Users> users = userRepository.findAll();
		return users;
	}
	
	@PostMapping("/saveuser")
	public Users saveNewUser(@RequestBody Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Users save = userRepository.save(user);
		return save;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		System.out.println(user);
		 String verify = this.userService.verify(user);
		 
		 return verify;
	}
}
