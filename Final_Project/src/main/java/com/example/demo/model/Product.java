package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="food")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 64)
	private String title;
	@Column(nullable = true, length = 225)
	private String img_food;
	@Column(nullable = false, length = 225)
	private String description_food;
	@Column(nullable = false, length = 225)
	private long price;
	@Column(nullable = false, length = 64)
	private String category_name;

//	public Product() {
//	}
//
//	public Product(Long id, String img_food, String description_food, int price, String category_name) {
//		this.id = id;
//		this.img_food = img_food;
//		this.description_food = description_food;
//		this.price = price;
//		this.category_name = category_name;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg_food() {
		return img_food;
	}

	public void setImg_food(String img_food) {
		this.img_food = img_food;
	}

	public String getDescription_food() {
		return description_food;
	}

	public void setDescription_food(String description_food) {
		this.description_food = description_food;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(long l) {
		this.price = l;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
}
