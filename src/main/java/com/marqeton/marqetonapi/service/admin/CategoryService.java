package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.marqeton.marqetonapi.model.Category;

public interface CategoryService {

	Category saveCategory(MultipartFile image, Category category);
	
	Category updateCategory(MultipartFile image, Category category);

	List<Category> getCategories();

	Category getCategoryById(Long id);

	public void deleteCategoryById(Long id);
	
}
