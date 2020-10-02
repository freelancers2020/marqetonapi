package com.marqeton.marqetonapi.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.constants.RestUrlConstants;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.service.admin.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_CATEGORIES, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Category> saveCategory(@RequestPart String categoryJson,
			@RequestPart MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Category category = null;
		try {
			category = mapper.readValue(categoryJson, Category.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Category>(category,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category savedCategory = categoryService.saveCategory(image, category);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_CATEGORIES, method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Category> updateCategory(@RequestPart String categoryJson,
			@RequestPart(required = false) MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Category category = null;
		try {
			category = mapper.readValue(categoryJson, Category.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Category>(category,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Category savedCategory = categoryService.updateCategory(image, category);
		return new ResponseEntity<Category>(savedCategory,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_CATEGORIES, method = RequestMethod.GET)
	public ResponseEntity<List<Category>> getCategory() {
		List<Category> categoryList = categoryService.getCategories();
		return new ResponseEntity<List<Category>>(categoryList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_CATEGORY, method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteCategoryById(@PathVariable("id")Long id) {
		categoryService.deleteCategoryById(id);
		return new ResponseEntity<Category>(HttpStatus.ACCEPTED);
	}
}