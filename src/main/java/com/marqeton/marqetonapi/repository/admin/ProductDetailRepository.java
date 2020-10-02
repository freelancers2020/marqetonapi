package com.marqeton.marqetonapi.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long> {

	
	ProductDetail findByProductAndIsPrimary(Product product, Integer isPrimary);
	
	List<ProductDetail> findByIsPrimary(Integer isPrimary);

	List<ProductDetail> findByProduct(Product product);

	@Modifying
	@Query("UPDATE ProductDetail pd SET pd.isPrimary = :isPrimary WHERE pd.product= :product")
	int updateIsPrimaryByProduct(@Param("isPrimary") int isPrimary, @Param("product") Product product);
}
