package com.marqeton.marqetonapi.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.marqeton.marqetonapi.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

	@Query("SELECT p FROM ProductDetail pd INNER JOIN pd.product p WHERE pd.isPrimary= :isPrimary")
	List<Product> findByProductDetailListIsPrimary(int isPrimary);
	
	Product findByIdAndProductDetailListIsPrimary(Long id, Integer integer);
}
