package com.qs.telotengo.user.dto;

import javax.validation.constraints.NotNull;


public class UserRequest {
	Long id;
	@NotNull
	String rut;
	@NotNull
	String name;
	@NotNull
	String mail;
	@NotNull
	String phone;
	String password;
	String country;
	@NotNull
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UsuarioRequest [id=" + id + ", rut=" + rut + ", name=" + name + ", mail=" + mail + ", phone="
				+ phone + ", password=" + password + ", country=" + country + ", address=" + address + ", status=" + status
				+ "]";
	}

}
