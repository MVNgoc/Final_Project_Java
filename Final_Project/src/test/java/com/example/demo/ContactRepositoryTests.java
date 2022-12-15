package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.demo.model.Contact;
import com.example.demo.model.User;
import com.example.demo.repository.ContactRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ContactRepositoryTests {
	
	@Autowired
	private ContactRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateContact() {
		Contact contact = new Contact();
		contact.setUsername("nghia");
		contact.setUseremail("hungnghia20101@gmail.com");
		contact.setSubject("test");
		contact.setContributions("day chi la test");
		contact.setDaycreate("test");
		contact.setTimecreate("2:19:03");
		
		Contact savedUser = repo.save(contact);
		Contact existContact = entityManager.find(Contact.class, savedUser.getId());
		
		assertThat(existContact.getUseremail()).isEqualTo(contact.getUseremail());
		
	}
}
