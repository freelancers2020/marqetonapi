package com.marqeton.marqetonapi.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_address")
public class Address {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="name", nullable=false)
    @NotEmpty()
    private String name;
	
	@Column(name="house_no", nullable=false)
    @NotEmpty()
    private String flatNo;
	
	@Column(name="street_address", nullable=false)
    @NotEmpty()
    private String streetAddress;
	
	@Column(name="postal_code", nullable=false)
    @NotEmpty()
    private String postalCode;
	
	@Column(name="city", nullable=false)
    @NotEmpty()
    private String city;
	
	@Column(name="state", nullable=false)
    @NotEmpty()
    private String state;
	
	@Column(name="country", nullable=false)
    @NotEmpty()
    private String country;
	
	@Column(name="phone", nullable=false)
    @NotEmpty()
    private String phone;
	
	@Column(name = "address_type", nullable=false)
    @NotEmpty()
    private String addressType;
	
	@Column(name="created_on", nullable=false)
    private Date createdOn;
	
	@Column(name="updated_on", nullable=false)
    private Date updatedOn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_address_fk0"))
	User user;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "shippingAddress", cascade = CascadeType.ALL)
	Order shippingOrder;
	
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "billingAddress", cascade = CascadeType.ALL)
	Order billingOrder;

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

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Order getShippingOrder() {
		return shippingOrder;
	}

	public void setShippingOrder(Order shippingOrder) {
		this.shippingOrder = shippingOrder;
	}

	public Order getBillingOrder() {
		return billingOrder;
	}

	public void setBillingOrder(Order billingOrder) {
		this.billingOrder = billingOrder;
	}	
	

}
