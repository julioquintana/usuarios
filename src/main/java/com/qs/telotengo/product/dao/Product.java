package com.qs.telotengo.product.dao;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class Product implements Serializable {

	private static final long serialVersionUID = 40L;
	@Id
	private String id;
	@NotNull
	private String idStore;
	@NotNull
	private String name;
	@NotNull
	private String tag;
	@NotNull
	private String details;
	@NotNull
	private Timestamp createDate;
	@NotNull
	private String userCreate;
	@NotNull
	private String type;
	@NotNull
	private Time timePreparation;
	private List<Photo> gallery;
	private boolean status;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdStore() {
		return idStore;
	}
	public void setIdStore(String idStore) {
		this.idStore = idStore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Time getTimePreparation() {
		return timePreparation;
	}
	public void setTimePreparation(Time timePreparation) {
		this.timePreparation = timePreparation;
	}
	public List<Photo> getGallery() {
		return gallery;
	}
	public void setGallery(List<Photo> gallery) {
		this.gallery = gallery;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Product() {
	}
	public Product(String id, @NotNull String idStore, @NotNull String name, @NotNull String tag,
			@NotNull String details, @NotNull Timestamp createDate, @NotNull String userCreate, @NotNull String type,
			@NotNull Time timePreparation, List<Photo> gallery, boolean status) {
		super();
		this.id = id;
		this.idStore = idStore;
		this.name = name;
		this.tag = tag;
		this.details = details;
		this.createDate = createDate;
		this.userCreate = userCreate;
		this.type = type;
		this.timePreparation = timePreparation;
		this.gallery = gallery;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", idStore=" + idStore + ", name=" + name + ", tag=" + tag + ", details=" + details
				+ ", createDate=" + createDate + ", userCreate=" + userCreate + ", type=" + type + ", timePreparation="
				+ timePreparation + ", gallery=" + gallery + ", status=" + status + "]";
	}

	
}
