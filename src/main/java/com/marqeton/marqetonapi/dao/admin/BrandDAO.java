package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Brand;

public interface BrandDAO {

	Brand saveBrand(Brand brand);

	List<Brand> getBrand();

	Brand getBrandById(Long id);

	public void deleteBrandById(Long id);

}
