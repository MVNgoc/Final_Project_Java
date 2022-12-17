package com.example.demo.model;

public class Name_Quantity {
	private Long quantity;
	private String name;
	
	
	public Name_Quantity() {
	}
	
	public Name_Quantity(Long quantity , String name) {
		this.quantity = quantity;
		this.name = name;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
