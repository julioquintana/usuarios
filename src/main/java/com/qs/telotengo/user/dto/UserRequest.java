package com.qs.telotengo.user.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

import com.qs.telotengo.user.dao.Address;

public class UserRequest {

	String id;
	@NotNull(message="El rut no puede ser nulo")
	String rut;
	@NotNull
	String name;
	@NotNull
	@Email
	String mail;
	String password;
	List<Address> addresses;
	@Value("true")
	boolean status;


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
		return "UserRequest [id=" + id + ", rut=" + rut + ", name=" + name + ", mail=" + mail + ", password=" + password
				+ ", addresses=" + addresses + ", status=" + status + "]";
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
