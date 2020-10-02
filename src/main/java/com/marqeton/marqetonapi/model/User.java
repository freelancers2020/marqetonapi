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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbl_user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name ="username",nullable=false)
	@NotEmpty
	private String username;

	@Column(name ="password", nullable=false)
	@NotEmpty
	private String password;

	@Column(name ="name", nullable=false)
	@NotEmpty()
	private String name;

	@Column(name ="email", nullable=true, unique=true)
	private String email;

	@Column(name="gender")
	private String gender;

	@Column(name ="mobile", nullable=true, unique=true)
	private String mobile;

	@Column(name ="status",nullable=false)
	@NotNull()
	private Integer status;

	@Column(name="created_on", nullable=true)
	private Date createdDate;

	@Column(name="updated_on", nullable=true)
	private Date updatedDate;

	@Column(name="dateofbirth", nullable=false)
	private Date dateofbirth;

	@OneToOne(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.ALL)
	Cart cart;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE, mappedBy="user")
	List<Reviews> reviewList;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "user", cascade = CascadeType.MERGE)
	Wishlist wishlist;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="user")
	List<Order> orderList;

	@Column(name="provider_key",nullable=true, unique=true)
	private String providerKey;

	@Column(name="provider_type",nullable=true, unique=true)
	private String providerType;

	@ManyToMany(fetch= FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinTable(
			name="tbl_user_role",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_user_role_fk0"))},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_user_role_fk1") )})
	private List<Role> roles;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="user")
	List<Address> address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getProviderKey() {
		return providerKey;
	}

	public void setProviderKey(String providerKey) {
		this.providerKey = providerKey;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<Reviews> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Reviews> reviewList) {
		this.reviewList = reviewList;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	/*
	 * public Cart getCart() { return cart; }
	 * 
	 * public void setCart(Cart cart) { this.cart = cart; }
	 */



}
