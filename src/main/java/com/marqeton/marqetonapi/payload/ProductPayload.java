package com.marqeton.marqetonapi.payload;

import java.util.List;

import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.model.Concern;
import com.marqeton.marqetonapi.model.Option;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductMultimedia;
import com.marqeton.marqetonapi.model.ProductdetailOption;

public class ProductPayload {
	private Long id;
	private Long productDetailId;
    private String name;
	private String description;
    private Float discountPercentage;
    private Integer returnable;
	private String sku;
	private Float actualPrice;
	private Float procuredPrice;
	private Float discountPrice;
	private Integer stock;
    private Integer status;
    private Integer isPrimary;
    private Brand brand;
    private List<Category> categoryList;
    private List<Category> subCategoryList;
    private List<Option> optionList;
    private List<ProductdetailOption> productDetailOptionList;
    private List<Concern> concernList;
    private List<ProductDetail> productVarients;
    private List<ProductMultimedia> productMultimediaList;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductDetailId() {
		return productDetailId;
	}
	public void setProductDetailId(Long productDetailId) {
		this.productDetailId = productDetailId;
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
	public Float getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public Integer getReturnable() {
		return returnable;
	}
	public void setReturnable(Integer returnable) {
		this.returnable = returnable;
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
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	public Float getProcuredPrice() {
		return procuredPrice;
	}
	public void setProcuredPrice(Float procuredPrice) {
		this.procuredPrice = procuredPrice;
	}
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public List<Category> getSubCategoryList() {
		return subCategoryList;
	}
	public void setSubCategoryList(List<Category> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}
	public List<Option> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<Option> optionList) {
		this.optionList = optionList;
	}
	public List<ProductdetailOption> getProductDetailOptionList() {
		return productDetailOptionList;
	}
	public void setProductDetailOptionList(List<ProductdetailOption> productDetailOptionList) {
		this.productDetailOptionList = productDetailOptionList;
	}
	public List<Concern> getConcernList() {
		return concernList;
	}
	public void setConcernList(List<Concern> concernList) {
		this.concernList = concernList;
	}
	public List<ProductDetail> getProductVarients() {
		return productVarients;
	}
	public void setProductVarients(List<ProductDetail> productVarients) {
		this.productVarients = productVarients;
	}
	public List<ProductMultimedia> getProductMultimediaList() {
		return productMultimediaList;
	}
	public void setProductMultimediaList(List<ProductMultimedia> productMultimediaList) {
		this.productMultimediaList = productMultimediaList;
	}
	
    
}
