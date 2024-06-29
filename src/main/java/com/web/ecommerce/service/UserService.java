package com.web.ecommerce.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.ecommerce.databaseutil.DatabaseUtil;
import com.web.ecommerce.entity.User;
import com.web.ecommerce.entity.UserUpdate;
import com.web.ecommerce.repository.UserRepository;
import com.web.ecommerce.repository.UsersUpdateRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private DatabaseUtil databaseUtil;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User getUserByEmail(String email) {
		return this.userRepository.getUserByEmail(email);
	}

	public boolean validatePassword(String rawPassword, String encodedPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User addUser(User user) {
		return this.userRepository.save(user);
	}

	public boolean authenticateUser(String username, String password) {
		return databaseUtil.validateUser(username, password);
	}

	public List<User> findAllUsers() {
		return databaseUtil.findAllUsers();
	}
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }


    public void sendEmailWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        mailSender.send(message);
    }
    
}
