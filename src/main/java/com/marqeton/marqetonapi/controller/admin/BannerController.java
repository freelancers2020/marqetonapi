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
import com.marqeton.marqetonapi.model.Banner;
import com.marqeton.marqetonapi.model.Brand;
import com.marqeton.marqetonapi.service.admin.BannerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class BannerController {
	
	@Autowired
	BannerService bannerService;
	
	@RequestMapping(value  = RestUrlConstants.ADMIN_BANNERS, method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Banner> saveBanner(@RequestPart String bannerJson,
			@RequestPart MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Banner banner = null;
		try {
			banner = mapper.readValue(bannerJson, Banner.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Banner>(banner,HttpStatus.CONFLICT);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Banner savedBanner = bannerService.saveBanner(image,banner);
		return new ResponseEntity<Banner>(savedBanner,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_BANNERS, method = RequestMethod.GET)
	public ResponseEntity<List<Banner>> getBanner() {
		List<Banner> bannerList = bannerService.getBanner();
		return new ResponseEntity<List<Banner>>(bannerList,HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_BANNER, method = RequestMethod.GET)
	public ResponseEntity<Banner> getBannerById(@PathVariable(value = "id")Long id) {
		Banner banner = bannerService.getBannerById(id);
		return new ResponseEntity<Banner>(banner,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_BANNER, method = RequestMethod.DELETE)
	public ResponseEntity<Banner> deleteBannerById(@PathVariable("id")Long id) {
		bannerService.deleteBannerById(id);
		return new ResponseEntity<Banner>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value  = RestUrlConstants.ADMIN_BANNERS, method = RequestMethod.PUT, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Banner> updateBanner(@RequestPart String bannerJson,
			@RequestPart(required = false) MultipartFile image) {
		ObjectMapper mapper = new ObjectMapper();
		Banner banner = null;
		try {
			banner = mapper.readValue(bannerJson, Banner.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Banner>(banner,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Banner savedBanner = bannerService.updateBanner(image,banner);
		return new ResponseEntity<Banner>(savedBanner,HttpStatus.CREATED);
	}

}
