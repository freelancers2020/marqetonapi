package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.CategoryDAO;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.repository.admin.CategoryRepository;

@Repository("CategoryRepository")
public class CategoryDAOImpl implements CategoryDAO{

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getCategory() {
		return new ArrayList<Category> ((List<Category>)categoryRepository.findByCategoryNull());
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}

}
