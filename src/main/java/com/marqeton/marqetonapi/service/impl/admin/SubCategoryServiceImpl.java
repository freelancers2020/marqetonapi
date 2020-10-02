package com.marqeton.marqetonapi.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.dao.admin.SubCategoryDAO;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.service.admin.SubCategoryService;
import com.marqeton.marqetonapi.utility.FileStorage;

@Service("SubCategoryService")
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	SubCategoryDAO subCategoryDAO;
	
	@Autowired
	FileStorage fileStorage;
	
	@Autowired
	private Environment env;

	@Override
	public Category saveSubCategory(MultipartFile image, Category subCategory) {
		/*
		 * Store Sub Category Image
		 */
		String relativePath = fileStorage.SUB_CATEGORY_DIR+subCategory.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,subCategory.getSku()+filePattern,relativePath);
		
		/*
		 * Decide the Sub Category Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String subCategoryServerDir = env.getProperty("app.subCategoryServerDir");
		String subCategoryUrl = domainName+subCategoryServerDir+subCategory.getSku()+"/"+subCategory.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());
		
		
		/*
		 * Save the Sub Category URL
		 */
		
		subCategory.setImages(subCategoryUrl);
		subCategory.setCreatedOn(new Date());
		subCategory.setUpdatedOn(new Date());
		
		
		Category savedCategory = subCategoryDAO.saveSubCategory(subCategory);
		return savedCategory;
	}

	@Override
	public Category updateSubCategory(MultipartFile image, Category subcategory) {
		
		if(image!=null) {
		String relativePath = fileStorage.SUB_CATEGORY_DIR+subcategory.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		
		/*
		 * Delete the existing Image if it exists
		 */
		fileStorage.deleteAll(relativePath);
		
		/*
		 * Store the Sub Category Image
		 */
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,subcategory.getSku()+filePattern,relativePath);

		/*
		 * Decide the  Sub Category Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String subCategoryServerDir = env.getProperty("app.subCategoryServerDir");

		String subCategoryUrl = domainName+subCategoryServerDir+subcategory.getSku()+"/"+subcategory.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Sub Category URL
		 */
		Category existingCategory = subCategoryDAO.getSubCategoryById(subcategory.getId());
		subcategory.setImages(subCategoryUrl);
		subcategory.setCreatedOn(existingCategory.getCreatedOn());
		subcategory.setUpdatedOn(new Date());
		
		}
		else {
			Category existingCategory = subCategoryDAO.getSubCategoryById(subcategory.getId());
			subcategory.setCreatedOn(existingCategory.getCreatedOn());
			subcategory.setUpdatedOn(new Date());
			subcategory.setImages(existingCategory.getImages());
		}
		Category savedCategory = subCategoryDAO.saveSubCategory(subcategory);
		return savedCategory;
	}

	@Override
	public List<Category> getSubCategories() {
		List<Category> savedList = subCategoryDAO.getSubCategories();
		return savedList;// TODO Auto-generated method stub
	}

	@Override
	public Category getSubCategoryById(Long id) {
		Category category = subCategoryDAO.getSubCategoryById(id);
		return category;
	}

	@Override
	public void deleteSubCategoryById(Long id) {
		Category subcategory = subCategoryDAO.getSubCategoryById(id);
		String relativePath = fileStorage.SUB_CATEGORY_DIR+subcategory.getSku();
		subCategoryDAO.deleteSubCategoryById(id);
		fileStorage.deleteAll(relativePath);
	}

	@Override
	public List<Category> getSubCategoriesByCategory(Long categoryId) {
		List<Category> subCategoryList = subCategoryDAO.getSubCategoriesByCategory(categoryId);
		return subCategoryList;
	}

	@Override
	public List<Category> getSubCategoriesByCategories(List<Category> categoryList) {
		List<Category> subCategoryList = subCategoryDAO.getSubCategoriesByCategories(categoryList);
		return subCategoryList;
	}

}
