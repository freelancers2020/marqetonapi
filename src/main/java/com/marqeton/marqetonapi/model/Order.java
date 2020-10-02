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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="order_number", nullable=false)
    @NotEmpty()
    private String orderNumber;
	
	@Column(name="order_date", nullable=false)
    private Date orderDate;
	
	@Column(name="payment_mode", nullable=false)
    @NotEmpty()
    private String paymentMode;
	
	@Column(name="payment_status", nullable=false)
    @NotEmpty()
    private String paymentStatus;
	
	@Column(name="total_price", nullable=false)
	@NotEmpty()
    private Float totalPrice;
	
	@Column(name="discount_price", nullable=false)
	@NotEmpty()
    private Float discountPrice;
	
	@Column(name="total_weight", nullable=false)
	@NotEmpty()
    private Float totalWeight;
	
	@Column(name="shipping_charge", nullable=false)
	@NotEmpty()
    private Float shippingCharges;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_order_fk0"))
	User user;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="shipping_address_id",foreignKey = @ForeignKey(name = "tbl_order_fk1"))
	Address shippingAddress;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="billing_address_id",foreignKey = @ForeignKey(name = "tbl_order_fk2"))
	Address billingAddress;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_status_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_order_fk3"))
	OrderStatus orderStatus;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="order")
	List<OrderDetail> orderDetailList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Float discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Float getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Float totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Float getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(Float shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}


}
