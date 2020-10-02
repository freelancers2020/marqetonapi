package com.marqeton.marqetonapi.repository.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marqeton.marqetonapi.model.Category;

public interface SubCategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByCategoryNotNull();
	
	List<Category> findByCategory_id(Long id);

	List<Category> findByCategoryIn(List<Category> categoryList);

}
