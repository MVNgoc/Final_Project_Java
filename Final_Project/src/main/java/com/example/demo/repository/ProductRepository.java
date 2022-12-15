package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT u FROM Product u WHERE u.category_name = ?1")
	Product findbyCategory_name(String category_name);
	
	@Query("SELECT u FROM Product u WHERE u.id = ?1")
	public Product findbyId(Long id);
}
