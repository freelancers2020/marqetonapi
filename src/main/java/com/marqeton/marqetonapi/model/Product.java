package com.marqeton.marqetonapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_product")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="status", nullable=false)
    @NotNull()
    private Integer status;
	
	@Column(name="created_on", nullable=false)
    private Date createdOn;
	
	@Column(name="updated_on", nullable=false)
    private Date updatedOn;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(
       name="tbl_product_category",
       joinColumns={@JoinColumn(name="PRODUCT_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_product_category_fk0"))},
       inverseJoinColumns={@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_product_category_fk1") )})
	//@JsonManagedReference(value = "category_product")
	private List<Category> category;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="brand_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_product_fk1"))
	Brand brand;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="product")
	@JsonManagedReference(value = "product_review")
	List<Reviews> reviewList;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="product")
	@JsonManagedReference(value = "product_productdetail")
	List<ProductDetail> productDetailList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	
	public List<Category> getCategory() {
		return category;
	}

	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<Reviews> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Reviews> reviewList) {
		this.reviewList = reviewList;
	}
	
	public List<ProductDetail> getProductDetailList() {
		return productDetailList;
	}

	public void setProductDetailList(List<ProductDetail> productDetailList) {
		this.productDetailList = productDetailList;
	}

}
