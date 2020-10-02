package com.marqeton.marqetonapi.model;

import java.util.Date;

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
@Table(name="tbl_order_detail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(name="shipping_date", nullable=false)
	@NotEmpty()
    private Date shippingDate;
	
	@Column(name="return_date", nullable=true)
    private Date returnDate;
	
	@Column(name="created_on", nullable=false)
    private Date createdOn;
	
	@Column(name="updated_on", nullable=false)
    private Date updatedOn;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_order_detail_fk0"))
	Order order;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_detail_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_order_detail_fk1"))
	//@JsonManagedReference(value="productdetail_orderdetail")
	ProductDetail productDetail;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="order_status_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_order_detail_fk2"))
	OrderStatus orderStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


	public ProductDetail getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
