package com.marqeton.marqetonapi.repository.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductMultimedia;

public interface ProductMultimediaRepository extends CrudRepository<ProductMultimedia, Long> {

	List<ProductMultimedia> findByProductDetail(ProductDetail productDetail);

	void deleteByProductDetail(ProductDetail productDetail);

}
