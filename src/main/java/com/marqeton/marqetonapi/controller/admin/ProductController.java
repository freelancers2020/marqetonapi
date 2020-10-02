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
import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.payload.ProductPayload;
import com.marqeton.marqetonapi.service.admin.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCTS, method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> saveProduct(@RequestPart String productPayloadJson, @RequestPart MultipartFile[] images) {
		ObjectMapper mapper = new ObjectMapper();
		ProductPayload productPayload = null;
		Product product = null;
		try {
			productPayload = mapper.readValue(productPayloadJson, ProductPayload.class);
		}catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Product>(product,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product savedProduct = productService.saveProduct(images, productPayload);
		return new ResponseEntity<Product>(savedProduct,HttpStatus.CREATED);
	}
	
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCTS, method = RequestMethod.GET)
	public ResponseEntity<List<ProductPayload>> getProducts(@RequestParam(name = "isPrimary", defaultValue = "false") Boolean isPrimary) {
		List<ProductPayload> productList = productService.getProducts(isPrimary);
		return new ResponseEntity<List<ProductPayload>>(productList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT_PRODUCTDETAILS, method = RequestMethod.GET)
	public ResponseEntity<List<ProductPayload>> getProductDetails(@PathVariable("id") Long id) {
		List<ProductPayload> productList = productService.getProductDetails(id);
		return new ResponseEntity<List<ProductPayload>>(productList,HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT_PRODUCTDETAILS, method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProductDetail> saveProductDetail(@RequestPart String productPayloadJson, @RequestPart MultipartFile[] images, @PathVariable(value = "id")Long id) {
		ObjectMapper mapper = new ObjectMapper();
		ProductPayload productPayload = null;
		ProductDetail productDetail = null;
		try {
			productPayload = mapper.readValue(productPayloadJson, ProductPayload.class);
		}catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<ProductDetail>(productDetail,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductDetail savedProductDetail = productService.saveProductDetail(images, productPayload,id);
		return new ResponseEntity<ProductDetail>(savedProductDetail,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT, method = RequestMethod.PUT,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Product> updateProduct(@RequestPart String productPayloadJson, @RequestPart MultipartFile[] images, @PathVariable(value = "id")Long id) {
		ObjectMapper mapper = new ObjectMapper();
		ProductPayload productPayload = null;
		Product product = null;
		try {
			productPayload = mapper.readValue(productPayloadJson, ProductPayload.class);
		}catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<Product>(product,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Product updatedProduct = productService.updateProduct(images, productPayload, id);
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.CREATED);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT_PRODUCTDETAIL, method = RequestMethod.PUT,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ProductPayload> updateProductDetail(@RequestPart String productPayloadJson, @RequestPart MultipartFile[] images, @PathVariable(value = "prodId") Long prodId, @PathVariable(value = "detailId")Long detailId) {
		ObjectMapper mapper = new ObjectMapper();
		ProductPayload productPayload = null;
		try {
			productPayload = mapper.readValue(productPayloadJson, ProductPayload.class);
		}catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<ProductPayload>(productPayload,HttpStatus.CONFLICT);
			// TODO Auto-generated catch block
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ProductPayload updatedProductPayload = productService.updateProductDetail(images, productPayload, prodId, detailId);
		return new ResponseEntity<ProductPayload>(updatedProductPayload,HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT_PRODUCTDETAIL, method = RequestMethod.DELETE)
	public ResponseEntity<ProductDetail> deleteProductDetail(@PathVariable(value = "prodId") Long prodId, @PathVariable(value = "detailId") Long detailId) {
		productService.deleteProductDetail(prodId, detailId);
		return new ResponseEntity<ProductDetail>(HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT_PRODUCTDETAIL_MULTIMEDIA, method = RequestMethod.DELETE)
	public ResponseEntity<ProductDetail> deleteProductMultimedia(@PathVariable(value = "prodId") Long prodId, @PathVariable(value = "detailId") Long detailId, @PathVariable(value = "multiId") Long multiId) {
		productService.deleteProductMultimedia(prodId, detailId,multiId);
		return new ResponseEntity<ProductDetail>(HttpStatus.OK);
	}
	
	@RequestMapping(value = RestUrlConstants.ADMIN_PRODUCT, method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long prodId) {
		productService.deleteProduct(prodId);
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
	

}
