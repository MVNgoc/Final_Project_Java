package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Food_order;

public interface Food_orderRepository extends JpaRepository<Food_order, Long>{
	
}
