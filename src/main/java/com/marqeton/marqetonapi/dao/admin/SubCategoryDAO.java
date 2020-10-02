package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Category;

public interface SubCategoryDAO {
	Category saveSubCategory(Category category);

	List<Category> getSubCategories();

	Category getSubCategoryById(Long id);

	public void deleteSubCategoryById(Long id);

	List<Category> getSubCategoriesByCategory(Long categoryId);

	List<Category> getSubCategoriesByCategories(List<Category> categoryList);

}
