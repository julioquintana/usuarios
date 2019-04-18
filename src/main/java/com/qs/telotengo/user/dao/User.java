package com.qs.telotengo.user.dao;

import java.io.Serializable;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.qs.telotengo.user.dto.util.Phone;



@Document
public class User implements Serializable{

	private static final long serialVersionUID = 40L;
	@Id
	String id;
	@NotNull
	String rut;
	@NotNull
	String name;
	@NotNull
	@Email(message="Invalid email format")
	String mail;
	@NotNull
	String phone;
	String password;
	@NotNull
	String country;
	@NotNull
	String address;
	boolean status;

	public User() {
		super();
	}
	public User(String id, String rut, String name, String mail, String phone, String password, String country,
			String address, boolean status) {
		super();
		this.id = id;
		this.rut = rut;
		this.name = name;
		this.mail = mail;
		this.phone = phone;
		this.password = password;
		this.country = country;
		this.address = address;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", name=" + name + ", mail=" + mail + ", phone="
				+ phone + ", password=" + password + ", country=" + country + ", address=" + address + ", status=" + status
				+ "]";
	}

}
