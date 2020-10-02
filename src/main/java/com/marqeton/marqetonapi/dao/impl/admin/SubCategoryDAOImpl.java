package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.SubCategoryDAO;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.repository.admin.SubCategoryRepository;

@Repository("SubCategoryRepository")
public class SubCategoryDAOImpl implements SubCategoryDAO{
	
	@Autowired
	SubCategoryRepository subCategoryRepository;

	@Override
	public Category saveSubCategory(Category category) {
		return subCategoryRepository.save(category);
	}

	@Override
	public List<Category> getSubCategories() {
		//return new ArrayList<Category> ((List<Category>)subCategoryRepository.findAll());
		
		return new ArrayList<Category> ((List<Category>) subCategoryRepository.findByCategoryNotNull());
				
	}

	@Override
	public Category getSubCategoryById(Long id) {
		return subCategoryRepository.findById(id).get();
	}

	@Override
	public void deleteSubCategoryById(Long id) {
		subCategoryRepository.deleteById(id);
	}

	@Override
	public List<Category> getSubCategoriesByCategory(Long categoryId) {
		
		return subCategoryRepository.findByCategory_id(categoryId);
	}

	@Override
	public List<Category> getSubCategoriesByCategories(List<Category> categoryList) {
		return subCategoryRepository.findByCategoryIn(categoryList);
	}

}
