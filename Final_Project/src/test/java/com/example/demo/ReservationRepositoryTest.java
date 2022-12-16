package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReservationRepositoryTest {
	@Autowired
	private ReservationRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateContact() {
		Reservation reser = new Reservation();
		reser.setUsername("nghia");
		reser.setUseremail("hungnghia20101@gmail.com");
		reser.setPhone_number("312321312");
		reser.setDayreservation("16/17/2022");
		reser.setTimereservation("7:30");
		reser.setPeople("5");
		reser.setContributions("day chi la test");
		reser.setDaycreate("14/12/2022");
		reser.setTimecreate("2:19:03");
		
		Reservation savedUser = repo.save(reser);
		Reservation existContact = entityManager.find(Reservation.class, savedUser.getId());
		
		assertThat(existContact.getUseremail()).isEqualTo(reser.getUseremail());
		
	}
}
