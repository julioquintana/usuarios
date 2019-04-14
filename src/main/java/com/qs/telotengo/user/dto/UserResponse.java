package com.qs.telotengo.user.dto;

import com.qs.telotengo.user.dao.User;

public class UserResponse {

	Long id;
	String rut;
	String name;
	String mail;
	String phone;
	String country;
	String address;
	boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public UserResponse() {
	}
	public UserResponse(User user) {
		this.id = user.getId();
		this.rut = user.getRut();
		this.name = user.getName();
		this.mail = user.getMail();
		this.phone = user.getPhone();
		this.country = user.getCountry();
		this.address = user.getAddress();
		this.status = user.isStatus();
	}

}
