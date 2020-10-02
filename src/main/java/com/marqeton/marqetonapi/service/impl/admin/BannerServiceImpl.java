package com.marqeton.marqetonapi.service.impl.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.dao.admin.BannerDAO;
import com.marqeton.marqetonapi.model.Banner;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.service.admin.BannerService;
import com.marqeton.marqetonapi.utility.FileStorage;

@Service("BannerService")
public class BannerServiceImpl implements BannerService{

	@Autowired
	BannerDAO bannerDAO;

	@Autowired
	FileStorage fileStorage;

	@Autowired
	private Environment env;
	

	@Override
	public Banner saveBanner(MultipartFile image, Banner banner) {
		/*
		 * Store the Brand Image
		 */
		String relativePath = fileStorage.BANNER_DIR+"/";
		fileStorage.createDirectory(relativePath);
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,filePattern,relativePath);

		/*
		 * Decide the Brand Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String bannerServerDir = env.getProperty("app.bannerServerDir");

		String bannerUrl = domainName+bannerServerDir+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Brand URL
		 */
		banner.setImageUrl(bannerUrl);
		banner.setCreatedOn(new Date());
		banner.setUpdatedOn(new Date());

		Banner savedBanner = bannerDAO.saveBanner(banner);
		return savedBanner;
	}


	@Override
	public List<Banner> getBanner() {
		List<Banner> savedBannerList = bannerDAO.getBanner();
		return savedBannerList;
	}


	@Override
	public Banner getBannerById(Long id) {
		Banner banner = bannerDAO.getBannerById(id);
		return banner;
	}


	@Override
	public void deleteBannerById(Long id) {
		Banner banner = bannerDAO.getBannerById(id);
		String relativePath = fileStorage.BANNER_DIR+banner.getImageUrl().substring((env.getProperty("app.domainName")+env.getProperty("app.brandServerDir")).length());
		fileStorage.delete(relativePath);
		bannerDAO.deleteBannerById(id);
		
	}


	@Override
	public Banner updateBanner(MultipartFile image, Banner banner) {
		String relativePath = fileStorage.BANNER_DIR+"/";
		fileStorage.createDirectory(relativePath);
		
		/*
		 * Delete the existing Image if it exists
		 */
		fileStorage.deleteAll(relativePath);
		
		/*
		 * Store the Brand Image
		 */
		String filePattern = fileStorage.getFilePattern();
		fileStorage.store(image,filePattern,relativePath);

		/*
		 * Decide the Brand Image url
		 */
		String domainName = env.getProperty("app.domainName");
		String bannerServerDir = env.getProperty("app.bannerServerDir");

		String bannerUrl = domainName+bannerServerDir+filePattern+"."+FilenameUtils.getExtension(image.getOriginalFilename());

		/*
		 * Save the Brand URL
		 */
		Banner existingBanner = bannerDAO.getBannerById(banner.getId());
		banner.setImageUrl(bannerUrl);
		banner.setCreatedOn(existingBanner.getCreatedOn());
		banner.setUpdatedOn(new Date());
		banner.setImageUrl(existingBanner.getImageUrl());
		
		Banner savedBanner = bannerDAO.saveBanner(banner);
		return savedBanner;
	}
}
