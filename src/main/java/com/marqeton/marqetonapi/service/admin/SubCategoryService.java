package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.model.Category;

public interface SubCategoryService {
    
	Category saveSubCategory(MultipartFile image,Category subcategory);
	
	Category updateSubCategory(MultipartFile image, Category subcategory);

	List<Category> getSubCategories();

	Category getSubCategoryById(Long id);
 
	public void deleteSubCategoryById(Long id);

	List<Category> getSubCategoriesByCategory(Long categoryId);

	List<Category> getSubCategoriesByCategories(List<Category> categoryList);

}
