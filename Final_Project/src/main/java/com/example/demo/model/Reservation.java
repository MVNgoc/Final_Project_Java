package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 64)
	private String username;
	
	@Column(nullable = false, length = 225)
	private String useremail;
	
	@Column(nullable = false, length = 15)
	private String phone_number;
	
	@Column(nullable = false)
	private String timereservation;
	
	@Column(nullable = false)
	private String dayreservation;
	
	@Column(nullable = false, length = 3)
	private String people;
	
	@Column(nullable = false, length = 225)
	private String contributions;
	
	@Column(nullable = false)
	private String timecreate;
	
	@Column(nullable = false)
	private String daycreate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getTimereservation() {
		return timereservation;
	}

	public void setTimereservation(String timereservation) {
		this.timereservation = timereservation;
	}

	public String getDayreservation() {
		return dayreservation;
	}

	public void setDayreservation(String dayreservation) {
		this.dayreservation = dayreservation;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getContributions() {
		return contributions;
	}

	public void setContributions(String contributions) {
		this.contributions = contributions;
	}

	public String getTimecreate() {
		return timecreate;
	}

	public void setTimecreate(String timecreate) {
		this.timecreate = timecreate;
	}

	public String getDaycreate() {
		return daycreate;
	}

	public void setDaycreate(String daycreate) {
		this.daycreate = daycreate;
	}
}
