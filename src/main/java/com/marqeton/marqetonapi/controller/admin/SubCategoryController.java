package com.marqeton.marqetonapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.constants.RestUrlConstants;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.service.admin.SubCategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class SubCategoryController {
	
	@Autowired
	SubCategoryService subCategoryService;
	
	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_SUB_CATEGORIES, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Category> saveSubCategory(@RequestPart String subcategoryJson, @RequestPart MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Category subCategory = null;
		try {
			subCategory = mapper.readValue(subcategoryJson, Category.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Category>(subCategory,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category savedCategory = subCategoryService.saveSubCategory(image,subCategory);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_SUB_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getSubCategories() {
		List<Category> subCategoryList = subCategoryService.getSubCategories();
		return new ResponseEntity<List<Category>>(subCategoryList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_CATEGORY_SUB_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getSubCategoriesByCategory(@PathVariable("categoryId")Long categoryId) {
		List<Category> subCategoryList = subCategoryService.getSubCategoriesByCategory(categoryId);
		return new ResponseEntity<List<Category>>(subCategoryList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_CATEGORIES_SUB_CATEGORIES, method = RequestMethod.POST)
	public ResponseEntity<List<Category>> getSubCategoriesByCategories(@RequestBody List<Category> categoryList) {
		List<Category> subCategoryList = subCategoryService.getSubCategoriesByCategories(categoryList);
		return new ResponseEntity<List<Category>>(subCategoryList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_SUB_CATEGORIES, method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Category> updateSubCategory(@RequestPart String subcategoryJson, @RequestPart(required = false) MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Category subCategory = null;
		try {
			subCategory = mapper.readValue(subcategoryJson, Category.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Category>(subCategory,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category savedCategory = subCategoryService.updateSubCategory(image, subCategory);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_SUB_CATEGORY, method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id")Long id) {
		subCategoryService.deleteSubCategoryById(id);
		return new ResponseEntity<Category>(HttpStatus.ACCEPTED);
	}
	
	
	
}
