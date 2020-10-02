package com.marqeton.marqetonapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_productdetail_option")
public class ProductdetailOption {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="unit",nullable=true)
	private String unit;
	
	@Column(name="value",nullable=false)
	@NotEmpty()
	private String value;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_detail_id",nullable = false, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_productdetail_option_fk0"))
	@JsonBackReference(value="productdetail_productdetailoption")
	ProductDetail productDetail;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="option_id",nullable = false, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_productdetail_option_fk1"))
	//@JsonManagedReference(value="productdetailoption_option")
	Option option;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public ProductDetail getProductDetail() {
		return productDetail;
	}


	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}


	public Option getOption() {
		return option;
	}


	public void setOption(Option option) {
		this.option = option;
	}
	
}
