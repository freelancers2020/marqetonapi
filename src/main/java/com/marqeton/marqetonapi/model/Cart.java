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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="quantity", nullable=false)
	@NotEmpty()
	private Integer quantity;

	@Column(name="total_quantity", nullable=false)
	@NotEmpty()
	private Integer totalQuantity;

	@Column(name="total_price", nullable=false)
	@NotEmpty()
	private Float totalPrice;

	@Column(name="created_on", nullable=false)
	private Date createdOn;

	@Column(name="updated_on", nullable=false)
	private Date updatedOn;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",foreignKey = @ForeignKey(name = "tbl_cart_fk1"))
	User user;

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="tbl_cart_product_detail",
			joinColumns={@JoinColumn(name="CART_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_cart_product_detail_fk0"))},
			inverseJoinColumns={@JoinColumn(name="PRODUCT_DETAIL_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_cart_product_detail_fk1") )})
	//@JsonManagedReference(value="productdetail_cart")
	private List<ProductDetail> productDetailList;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
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


	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }

	public List<ProductDetail> getProductDetailList() {
		return productDetailList;
	}

	public void setProductDetailList(List<ProductDetail> productDetailList) {
		this.productDetailList = productDetailList;
	}


}
