package com.security.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/normal")
public class NormalController {
		
	@GetMapping("/intro")
	public String intro() {
		return "hello this is unsecured page ";
	}
}
