package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.BrandDAO;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.repository.admin.BrandRepository;

@Repository("BrandRepository")
public class BrandDAOImpl implements BrandDAO{

	@Autowired
	BrandRepository brandRepository;

	@Override
	public Brand saveBrand(Brand brand) {

		return brandRepository.save(brand);
	}

	@Override
	public List<Brand> getBrand() {

		return new ArrayList<Brand>((List<Brand>)brandRepository.findAll());
	}

	@Override
	public Brand getBrandById(Long id) {
		return brandRepository.findById(id).get();
	}

	@Override
	public void deleteBrandById(Long id) {
		brandRepository.deleteById(id);
		
	}

}
