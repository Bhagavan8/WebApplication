package com.web.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.ecommerce.entity.User;
import com.web.ecommerce.entity.UserUpdate;
import com.web.ecommerce.service.UserService;
import com.web.ecommerce.service.UsersUpdateService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private UsersUpdateService usersUpdateService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		System.out.println(username);
		System.out.println(password);
		System.out.println(bCryptPasswordEncoder.encode(password));
		if (userService.authenticateUser(username, password)) {
			return ResponseEntity.ok("{\"message\": \"Login successful\"}");
		} else {
			return ResponseEntity.status(401).body("{\"message\": \"Invalid credentials\"}");
		}
	}

	@GetMapping("/userList")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/sendEmail")
	public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
		userService.sendSimpleEmail(to, subject, text);
		return "Email sent successfully";
	}

	@PutMapping("/updateUser/{id}")
	public UserUpdate updateUser(@PathVariable Long id, @RequestBody UserUpdate user) {
		user.setId(id);
		return usersUpdateService.saveUser(user);
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable Long id) {
		usersUpdateService.deleteUser(id);
	}
}
