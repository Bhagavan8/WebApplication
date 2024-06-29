package com.web.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.ecommerce.entity.UserUpdate;

@Repository
public interface UsersUpdateRepository extends JpaRepository<UserUpdate, Long> { 
	
	

}
