package com.web.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web.ecommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Optional<User> findByUsername(String username);
    
    @Query("select v from User v where v.email = :email")
	public User getUserByEmail(@Param("email") String email);
    
    Optional<User> findByEmail(String email);
    
    
 
}

