package com.example.demo.model;

public class Name_Quantity {
	private String quantity;
	private String name;
	
	
	public Name_Quantity() {
	}
	
	public Name_Quantity(String quantity , String name) {
		this.quantity = quantity;
		this.name = name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
