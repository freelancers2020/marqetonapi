package com.marqeton.marqetonapi.dao.admin;

import java.util.List;
import java.util.Optional;

import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductMultimedia;
import com.marqeton.marqetonapi.payload.ProductPayload;

public interface ProductDAO {

	Product saveProduct(Product product);
	List<Product> getProducts();
	Optional<Product> getProduct(Long id);
	List<ProductDetail> getPrimaryProductDetails();
	ProductDetail saveProductDetail(ProductDetail productDetail);
	Product getProductWithPrimaryProductDetail(Long id);
	Optional<ProductDetail> getProductDetail(Long id);
	List<ProductMultimedia> getProductDetailMultimedia(ProductDetail productDetail);
	void deleteProductDetail(ProductDetail productDetail);
	Optional<ProductMultimedia> getProductMultimedia(Long id);
	void deleteProductMultimedia(ProductMultimedia productMultimedia);
	List<ProductDetail> getProductDetailByProduct(Product product);
	void deleteProductMultimediaByProductDetail(ProductDetail productDetail);
	void deleteProductDetailOptionByProductDetail(ProductDetail productDetail);
	void deleteProduct(Product product);
	int updateIsPrimaryByProduct(int isPrimary, Product product);
	
}
