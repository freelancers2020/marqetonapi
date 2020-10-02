package com.marqeton.marqetonapi.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.model.Banner;

public interface BannerService {

	Banner saveBanner(MultipartFile image, Banner banner);

	List<Banner> getBanner();

	Banner getBannerById(Long id);

	void deleteBannerById(Long id);

	Banner updateBanner(MultipartFile image, Banner banner);

}
