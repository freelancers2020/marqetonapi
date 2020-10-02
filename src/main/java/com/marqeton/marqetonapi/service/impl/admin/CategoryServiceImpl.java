package com.marqeton.marqetonapi.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.dao.admin.CategoryDAO;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.service.admin.CategoryService;
import com.marqeton.marqetonapi.utility.FileStorage;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	FileStorage fileStorage;

	@Autowired
	private Environment env;

	@Override
	public Category saveCategory(MultipartFile image, Category category) {
		/*
		 * Store the Category Image
		 */
		String relativePath = fileStorage.CATEGORY_DIR+category.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,category.getSku()+filePattern,relativePath);

		/*
		 * Decide the Category Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String categoryServerDir = env.getProperty("app.categoryServerDir");

		String categoryUrl = domainName+categoryServerDir+category.getSku()+"/"+category.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Category URL
		 */
		category.setImages(categoryUrl);
		
		category.setCreatedOn(new Date());
		category.setUpdatedOn(new Date());

		Category savedCategory = categoryDAO.saveCategory(category);
		return savedCategory;
	}

	@Override
	public Category updateCategory(MultipartFile image, Category category) {
		String relativePath = fileStorage.CATEGORY_DIR+category.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		
		/*
		 * Delete the existing Image if it exists
		 */
		fileStorage.deleteAll(relativePath);
		
		/*
		 * Store the Category Image
		 */
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,category.getSku()+filePattern,relativePath);

		/*
		 * Decide the Category Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String categoryServerDir = env.getProperty("app.categoryServerDir");

		String categoryUrl = domainName+categoryServerDir+category.getSku()+"/"+category.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Category URL
		 */
		Category existingCategory = categoryDAO.getCategoryById(category.getId());
		category.setImages(categoryUrl);
		category.setCreatedOn(existingCategory.getCreatedOn());
		category.setUpdatedOn(new Date());
		category.setImages(existingCategory.getImages());
		
		Category savedCategory = categoryDAO.saveCategory(category);
		return savedCategory;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> savedList = categoryDAO.getCategory();
		return savedList;
	}

	@Override
	public Category getCategoryById(Long id) {
		Category category = categoryDAO.getCategoryById(id);
		return category;
	}

	@Override
	public void deleteCategoryById(Long id) {
		Category category = categoryDAO.getCategoryById(id);
		String relativePath = fileStorage.CATEGORY_DIR+category.getSku();
		categoryDAO.deleteCategoryById(id);
		fileStorage.deleteAll(relativePath);
		
	}

}
