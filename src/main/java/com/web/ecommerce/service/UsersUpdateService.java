package com.web.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.ecommerce.entity.UserUpdate;
import com.web.ecommerce.repository.UsersUpdateRepository;

@Service
public class UsersUpdateService {
	@Autowired
	private UsersUpdateRepository usersUpdateRepository;

	public UserUpdate saveUser(UserUpdate user) {
		return usersUpdateRepository.save(user);
	}

	public void deleteUser(Long id) {
		usersUpdateRepository.deleteById(id);
	}

}
