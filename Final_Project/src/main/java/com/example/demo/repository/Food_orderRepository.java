package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Food_order;

public interface Food_orderRepository extends JpaRepository<Food_order, Long>{
	@Query("SELECT u from Food_order u Where u.id = ?1")
    Food_order getFood_orderById(Long id); 
}
