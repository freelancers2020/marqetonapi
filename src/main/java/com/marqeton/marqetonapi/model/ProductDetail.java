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
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbl_product_detail")
public class ProductDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="name", nullable=false)
    @NotEmpty()
    private String name;
	
	@Column(name="description", nullable=false)
    @NotEmpty()
    private String description;

	@Column(name="sku", nullable=false, unique=true)
	@NotEmpty()
	private String sku;

	@Column(name="actual_price", nullable=false)
	@NotNull()
	private Float actualPrice;

	@Column(name="discount_price", nullable=false)
	@NotNull()
	private Float discountPrice;
	
	@Column(name="procured_price", nullable=false)
	@NotNull()
	private Float procuredPrice;

	@Column(name="stock", nullable=false)
	@NotNull()
	private Integer stock;

	@Column(name="rating")
	private double rating;
	
	@Column(name="returnable", nullable=false)
	@NotNull()
	private Integer returnable;
	
	@Column(name="discount_percentage", nullable = true)
	private float discountPercentage;
	
	@Column(name="status", nullable=false)
	@NotNull()
	private Integer status;

	@Column(name="created_on", nullable=false)
	private Date createdOn;

	@Column(name="updated_on", nullable=false)
	private Date updatedOn;
	
	@Column(name="is_primary", nullable=false)
	private Integer isPrimary;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy="productDetail")
	@JsonManagedReference(value="productdetail_productdetailoption")
	List<ProductdetailOption> productdetailOptionList;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL, mappedBy="productDetail")
	@JsonManagedReference(value="productdetail_multimedia")
	List<ProductMultimedia> multimediaList;
	
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy="productDetail")
	@JsonBackReference(value="productdetail_orderdetail")
	List<OrderDetail> orderDetailList;

	@ManyToMany(mappedBy = "productDetailList")
	@JsonBackReference(value="productdetail_cart")
	private List <Cart> cartList;
	
	@ManyToMany(mappedBy = "productDetailList")
	@JsonBackReference(value="productdetail_wishlist")
	private List <Wishlist> wishlists;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="product_id", nullable = true, updatable = true, insertable = true,foreignKey = @ForeignKey(name = "tbl_product_detail_fk0"))
	@JsonBackReference(value = "product_productdetail")
	Product product;
	
	@ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
       name="tbl_productdetail_concern",
       joinColumns={@JoinColumn(name="PRODUCTDETAIL_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_productdetail_concern_fk0"))},
       inverseJoinColumns={@JoinColumn(name="CONCERN_ID", referencedColumnName="ID",foreignKey = @ForeignKey(name="tbl_productdetail_concern_fk1") )})
	//@JsonManagedReference(value="concern_productdetail")
    private List<Concern> concerns;
	
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

	public List<ProductdetailOption> getProductdetailOptionList() {
		return productdetailOptionList;
	}

	public void setProductdetailOptionList(List<ProductdetailOption> productdetailOptionList) {
		this.productdetailOptionList = productdetailOptionList;
	}

	public List<ProductMultimedia> getMultimediaList() {
		return multimediaList;
	}

	public void setMultimediaList(List<ProductMultimedia> multimediaList) {
		this.multimediaList = multimediaList;
	}

	public List<Cart> getCartList() {
		return cartList;
	}

	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Concern> getConcerns() {
		return concerns;
	}

	public void setConcerns(List<Concern> concerns) {
		this.concerns = concerns;
	}

}
