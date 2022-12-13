package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findbyEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findbyUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.phone = ?1")
	User findbyPhone(String phone);
	
	@Query("SELECT u from User u Where u.username = ?1")
    public User getUserByUsername(String username); 
}
