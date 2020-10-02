package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.BannerDAO;
import com.marqeton.marqetonapi.model.Banner;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.repository.admin.BannerRepository;

@Repository("BannerRepository")
public class BannerDAOImpl implements BannerDAO{
	
	@Autowired
	BannerRepository bannerRepository;

	@Override
	public Banner saveBanner(Banner banner) {
		return bannerRepository.save(banner);
	}

	@Override
	public List<Banner> getBanner() {
		return new ArrayList<Banner>((List<Banner>)bannerRepository.findAll());
	}

	@Override
	public Banner getBannerById(Long id) {
		return bannerRepository.findById(id).get();
	}

	@Override
	public void deleteBannerById(Long id) {
		bannerRepository.deleteById(id);
		
	}
	
	

}
