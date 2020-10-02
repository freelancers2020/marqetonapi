package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.model.Brand;

public interface BrandService {

	Brand saveBrand(MultipartFile image, Brand brand);
	
	Brand updateBrand(MultipartFile image, Brand brand);

	List<Brand> getBrand();

	Brand getBrandById(Long id);

	public void deleteBrandById(Long id);

}
