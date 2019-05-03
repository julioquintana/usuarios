package com.qs.telotengo.user.dto;
import java.util.List;


import com.qs.telotengo.user.dao.User;
import com.qs.telotengo.user.dao.Address;

public class UserResponse {
	
	String id;
	String rut;
	String name;
	String mail;
	List<Address> addresses;
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
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.rut = user.getRut();
		this.name = user.getName();
		this.mail = user.getMail();
		this.status = user.isStatus();
		this.addresses = user.getAddresses();
	}
	
	public UserResponse() {
	}
	
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", rut=" + rut + ", name=" + name + ", mail=" + mail + ", addresses="
				+ addresses + ", status=" + status + "]";
	}
	



	
}
