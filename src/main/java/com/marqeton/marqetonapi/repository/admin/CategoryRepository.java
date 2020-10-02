package com.marqeton.marqetonapi.repository.admin;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.marqeton.marqetonapi.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findByCategoryNull();

}
