 package com.marqeton.marqetonapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="tbl_category")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(name="name", nullable=false)
    @NotEmpty()
    private String name;
	
	@Column(name="description", nullable=false)
    @NotEmpty()
    private String description;
	
	@Column(name="images")
    private String images;
	
	@Column(name="sku", nullable=false,unique = true)
	@NotEmpty()
	private String sku;
	
	@Column(name="status", nullable=false)
	@NotNull()
    private Integer status;
	
	@Column(name="percentage_discount", nullable=false)
    @NotNull()
    private Integer percentageDiscount;
	
	@Column(name="created_on", nullable=false)
    private Date createdOn;
	
	@Column(name="updated_on", nullable=false)
    private Date updatedOn;
	
	@ManyToOne()
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

	@OneToMany
	@JsonBackReference(value = "category_subcategory")
	@JoinColumn(name = "category_id")
	private List<Category> subCategory;
	
	@ManyToMany(mappedBy = "category")
	@JsonBackReference(value = "category_product")
    private List <Product> products;
	 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPercentageDiscount() {
		return percentageDiscount;
	}

	public void setPercentageDiscount(Integer percentageDiscount) {
		this.percentageDiscount = percentageDiscount;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(List<Category> subCategory) {
		this.subCategory = subCategory;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
