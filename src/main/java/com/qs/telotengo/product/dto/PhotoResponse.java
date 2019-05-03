package com.qs.telotengo.product.dto;

import com.qs.telotengo.product.dao.Photo;

public class PhotoResponse {
	
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
	
	public PhotoResponse() {
	}
	
	public PhotoResponse(Photo photo) {
		super();
		this.id = photo.getId();
		this.name = photo.getName();
		this.primary = photo.isPrimary();
		this.status = photo.isStatus();
	}
	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + ", primary=" + primary + ", status=" + status + "]";
	}
	
}
