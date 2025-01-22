package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.dto.UserDTO;
import com.java.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signIn")
	public String signIn() {return "signIn";}
	
	@GetMapping("/signUp")
	public String signUp() {return "signUp";}
	
	@PostMapping("/signUp")
	public String signUp(@ModelAttribute UserDTO user) {
		return userService.save(user);
	}
	
}
