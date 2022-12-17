package com.example.demo.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Food_orderConfirm {
	private ArrayList<Name_Quantity> name_quantity;
	private String username;
	private String phone_number;
	private String user_address;
	private long total_price;
	private String status;

	public Food_orderConfirm() {
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Name_Quantity> getFood_name() {
		return name_quantity;
	}

	public void setFood_name(ArrayList<Name_Quantity> name_Quantity) {
		this.name_quantity = name_Quantity;
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
