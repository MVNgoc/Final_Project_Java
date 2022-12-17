package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Reservation;
import com.example.demo.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	@Query("SELECT u FROM Reservation u WHERE u.username = ?1")
	List<Reservation> findbyUsername(String username);
	

}
