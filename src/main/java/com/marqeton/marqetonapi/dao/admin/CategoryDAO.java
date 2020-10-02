package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Category;

public interface CategoryDAO {

	Category saveCategory(Category category);

	List<Category> getCategory();

	Category getCategoryById(Long id);

	public void deleteCategoryById(Long id);

}
