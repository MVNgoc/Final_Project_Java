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
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
	@Autowired
	ProductRepository repoProduct;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateContact() {
		Product product = new Product();
		product.setCategory_name("Nướng");
		product.setDescription_food("Đây là test");
		product.setImg_food("test.jpg");
		product.setPrice(24000);
		product.setTitle("test");
		
		Product savedUser = repoProduct.save(product);
		Contact existContact = entityManager.find(Contact.class, savedUser.getId());
		
	}
}
