package com.qs.telotengo.user.dao;

public class Address{

	private String id;
	private String country;
	private String city;
	private String comuna;
	private String address;
	private String phone;
	private boolean primary;
	private boolean status;
	 
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", country=" + country + ", city=" + city + ", comuna=" + comuna + ", address="
				+ address + ", phone=" + phone + ", primary=" + primary + ", status=" + status + "]";
	}
	
	
	

}
