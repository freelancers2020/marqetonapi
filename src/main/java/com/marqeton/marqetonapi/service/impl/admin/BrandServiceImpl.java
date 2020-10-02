package com.marqeton.marqetonapi.service.impl.admin;


import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.dao.admin.BrandDAO;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.service.admin.BrandService;
import com.marqeton.marqetonapi.utility.FileStorage;

@Service("BrandService")
public class BrandServiceImpl implements BrandService{

	@Autowired
	BrandDAO brandDAO;

	@Autowired
	FileStorage fileStorage;

	@Autowired
	private Environment env;

	@Override
	public Brand saveBrand(MultipartFile image, Brand brand) {
		/*
		 * Store the Brand Image
		 */
		String relativePath = fileStorage.BRAND_DIR+brand.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,brand.getSku()+filePattern,relativePath);

		/*
		 * Decide the Brand Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String brandServerDir = env.getProperty("app.brandServerDir");

		String brandUrl = domainName+brandServerDir+brand.getSku()+"/"+brand.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Brand URL
		 */
		brand.setImageUrl(brandUrl);
		brand.setCreatedOn(new Date());
		brand.setUpdatedOn(new Date());

		Brand savedBrand = brandDAO.saveBrand(brand);
		return savedBrand;
	}

	@Override
	public List<Brand> getBrand() {
		List<Brand> savedList = brandDAO.getBrand();
		return savedList;
	}

	@Override
	public Brand getBrandById(Long id) {
		Brand brand = brandDAO.getBrandById(id);
		return brand;
	}

	@Override
	public void deleteBrandById(Long id) {
		Brand brand = brandDAO.getBrandById(id);
		String relativePath = fileStorage.BRAND_DIR+brand.getSku();
		fileStorage.deleteAll(relativePath);
		brandDAO.deleteBrandById(id);	
	}

	@Override
	public Brand updateBrand(MultipartFile image, Brand brand) {
		
		String relativePath = fileStorage.BRAND_DIR+brand.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		
		/*
		 * Delete the existing Image if it exists
		 */
		fileStorage.deleteAll(relativePath);
		
		/*
		 * Store the Brand Image
		 */
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,brand.getSku()+filePattern,relativePath);

		/*
		 * Decide the Brand Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String brandServerDir = env.getProperty("app.brandServerDir");

		String brandUrl = domainName+brandServerDir+brand.getSku()+"/"+brand.getSku()+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Brand URL
		 */
		Brand existingBrand = brandDAO.getBrandById(brand.getId());
		brand.setImageUrl(brandUrl);
		brand.setCreatedOn(existingBrand.getCreatedOn());
		brand.setUpdatedOn(new Date());
		brand.setImageUrl(existingBrand.getImageUrl());
		
		Brand savedBrand = brandDAO.saveBrand(brand);
		return savedBrand;
	}

}
