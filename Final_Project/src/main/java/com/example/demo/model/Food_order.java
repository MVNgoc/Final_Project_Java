package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_order")
public class Food_order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false, length = 64)
	private String food_name;
	@Column(nullable = true, length = 225)
	private String img_food;
	@Column(nullable = false, length = 10)
	private long quantity;
	@Column(nullable = false, length = 64)
	private String username;
	@Column(nullable = false, length = 15)
	private String phone_number;
	@Column(nullable = false, length = 64)
	private String email;
	@Column(nullable = false, length = 255)
	private String user_address;
	@Column(nullable = false, length = 225)
	private long total_price;
	@Column(nullable = true, length = 20)
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFood_name() {
		return food_name;
	}

	public void setFood_name(String food_name) {
		this.food_name = food_name;
	}

	public String getImg_food() {
		return img_food;
	}

	public void setImg_food(String img_food) {
		this.img_food = img_food;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(long total_price) {
		this.total_price = total_price;
	}
}
