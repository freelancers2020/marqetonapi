package com.marqeton.marqetonapi.model;

import java.util.Date;

public class ProductDetail {
	private Integer id;
    private String name;
    private String description;
	private String sku;
	private Float actualPrice;
	private Float discountPrice;
	private Float procuredPrice;
	private Integer stock;
	private double rating;
	private Integer returnable;
	private float discountPercentage;
	private Integer status;
	private Date createdOn;
	private Date updatedOn;
	private Integer isPrimary;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Float getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Float actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Float getProcuredPrice() {
		return procuredPrice;
	}

	public void setProcuredPrice(Float procuredPrice) {
		this.procuredPrice = procuredPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Integer isReturnable() {
		return returnable;
	}

	public void setReturnable(Integer returnable) {
		this.returnable = returnable;
	}

	public float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public Integer getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}

}
