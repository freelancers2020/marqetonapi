package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.exception.ProductDetailNotFoundException;
import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.payload.ProductPayload;

public interface ProductService {

	Product saveProduct(MultipartFile[] images,ProductPayload productPayload);
	List<ProductPayload> getProducts(Boolean isPrimary);
	ProductDetail saveProductDetail(MultipartFile[] images, ProductPayload productPayload, Long id);
	
	Product updateProduct(MultipartFile[] images, ProductPayload productPayload, Long id);
	void deleteProductDetail(Long prodId, Long detailId);
	void deleteProductMultimedia(Long prodId, Long detailId, Long multiId);
	List<ProductPayload> getProductDetails(Long id);
	ProductPayload updateProductDetail(MultipartFile[] images, ProductPayload productPayload, Long prodId,
			Long detailId);
	void deleteProduct(Long prodId);
}
