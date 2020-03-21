package com.briup.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cms_customer")
public class Customer {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String userNamer;
	private String password;
	
	public Customer() {}

	public Customer(String userNamer, String password) {
		super();
		this.userNamer = userNamer;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNamer() {
		return userNamer;
	}

	public void setUserNamer(String userNamer) {
		this.userNamer = userNamer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userNamer=" + userNamer + ", password=" + password + "]";
	}
	
	
}
