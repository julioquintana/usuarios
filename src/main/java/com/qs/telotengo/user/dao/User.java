package com.qs.telotengo.user.dao;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document
public class User implements Serializable{

	private static final long serialVersionUID = 40L;
	@Id
	private String id;
	@NotNull
	private String rut;
	@NotNull
	private String name;
	@NotNull
	@Email(message="Invalid email format")
	private String mail;
	@NotNull
	private String password;
	
	private List<Address> addresses;
	private boolean status;
	
	public User() {
		
	}
	public User(String id, @NotNull String rut, @NotNull String name,
			@NotNull @Email(message = "Invalid email format") String mail, @NotNull String password,
			List<Address> addresses, boolean status) {
		super();
		this.id = id;
		this.rut = rut;
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.addresses = addresses;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", rut=" + rut + ", name=" + name + ", mail=" + mail + ", password=" + password
				+ ", addresses=" + addresses + ", status=" + status + "]";
	}

	

}
