package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contact")
public class Contact {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 64)
	private String username;
	
	@Column(nullable = false, length = 225)
	private String useremail;
	
	@Column(nullable = false, length = 225)
	private String subject;
	
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
