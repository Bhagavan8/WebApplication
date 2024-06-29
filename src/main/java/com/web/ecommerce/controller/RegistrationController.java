package com.web.ecommerce.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ecommerce.entity.User;
import com.web.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public Map<String, Integer> register(@RequestParam("username") String username, @RequestParam("email") String email,
			@RequestParam("password") String password,@RequestParam("name") String name ) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setName(name);

		userService.saveUser(user);
		Map<String, Integer> response = new HashMap<>();
		response.put("status", 200);
		return response;
	}
}
