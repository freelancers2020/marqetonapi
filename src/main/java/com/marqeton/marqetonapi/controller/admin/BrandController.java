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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.constants.RestUrlConstants;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.model.Category;
import com.marqeton.marqetonapi.service.admin.BrandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class BrandController {

	@Autowired
	BrandService brandService;

	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_BRANDS, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Brand> saveBrand(@RequestPart String brandJson,
			@RequestPart MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Brand brand = null;
		try {
			brand = mapper.readValue(brandJson, Brand.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Brand>(brand,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Brand savedBrand = brandService.saveBrand(image,brand);
		return new ResponseEntity<Brand>(savedBrand,HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_BRANDS, method = RequestMethod.GET)
	public ResponseEntity<List<Brand>> getBrand() {
		List<Brand> brandList = brandService.getBrand();
		return new ResponseEntity<List<Brand>>(brandList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_BRAND, method = RequestMethod.GET)
	public ResponseEntity<Brand> getBrandById(@PathVariable(value = "id")Long id) {
		Brand brand = brandService.getBrandById(id);
		return new ResponseEntity<Brand>(brand,HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_BRAND, method = RequestMethod.DELETE)
	public ResponseEntity<Brand> deleteBrandById(@PathVariable("id")Long id) {
		brandService.deleteBrandById(id);
		return new ResponseEntity<Brand>(HttpStatus.ACCEPTED);
	}
	
	@CrossOrigin
	@RequestMapping(value  = RestUrlConstants.ADMIN_BRANDS, method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Brand> updateBrand(@RequestPart String brandJson,
			@RequestPart(required = false) MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Brand brand = null;
		try {
			brand = mapper.readValue(brandJson, Brand.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Brand>(brand,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Brand savedBrand = brandService.updateBrand(image,brand);
		return new ResponseEntity<Brand>(savedBrand,HttpStatus.CREATED);
	}

}
