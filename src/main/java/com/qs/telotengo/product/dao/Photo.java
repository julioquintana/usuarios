package com.qs.telotengo.product.dao;

import com.qs.telotengo.product.dto.PhotoRequest;

public class Photo{

	private String id;
	private String name;
	private boolean primary;
	private boolean status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	

	public Photo(String id, String name, boolean primary, boolean status) {
		super();
		this.id = id;
		this.name = name;
		this.primary = primary;
		this.status = status;
	}

	public Photo(PhotoRequest photoRequest) {
		super();
		this.id = photoRequest.getId();
		this.name = photoRequest.getName();
		this.primary = photoRequest.isPrimary();
		this.status = photoRequest.isStatus();
	}
	public Photo() {
	}
	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + ", primary=" + primary + ", status=" + status + "]";
	}
	 

	

}
