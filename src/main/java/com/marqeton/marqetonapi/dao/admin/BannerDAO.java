package com.marqeton.marqetonapi.dao.admin;

import java.util.List;

import com.marqeton.marqetonapi.model.Banner;

public interface BannerDAO {

	Banner saveBanner(Banner banner);

	List<Banner> getBanner();

	Banner getBannerById(Long id);

	void deleteBannerById(Long id);

}
