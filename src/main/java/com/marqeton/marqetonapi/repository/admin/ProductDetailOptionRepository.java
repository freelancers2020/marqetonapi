package com.marqeton.marqetonapi.repository.admin;

import org.springframework.data.repository.CrudRepository;

import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductdetailOption;

public interface ProductDetailOptionRepository extends CrudRepository<ProductdetailOption, Long>{
	public void deleteByProductDetail(ProductDetail productDetail);
}
