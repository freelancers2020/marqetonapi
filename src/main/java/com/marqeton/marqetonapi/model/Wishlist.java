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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_wishlist")
public class Wishlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="created_on", nullable=false)
    private Date createdOn;
	
	@Column(name="updated_on", nullable=false)
    private Date updatedOn;

	@ManyToMany(cascade=CascadeType.MERGE)
	@JoinTable(
			name="tbl_wishlist_product_detail",
			joinColumns={@JoinColumn(name="WISHLIST_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_wishlist_product_detail_fk0"))},
			inverseJoinColumns={@JoinColumn(name="PRODUCT_DETAIL_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_wishlist_product_detail_fk1") )})
	//@JsonManagedReference(value="productdetail_wishlist")
	private List<ProductDetail> productDetailList;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",foreignKey = @ForeignKey(name = "tbl_wishlist_fk0"))
	User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ProductDetail> getProductDetailList() {
		return productDetailList;
	}

	public void setProductDetailList(List<ProductDetail> productDetailList) {
		this.productDetailList = productDetailList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
