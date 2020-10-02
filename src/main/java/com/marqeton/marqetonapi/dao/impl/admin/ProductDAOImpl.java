package com.marqeton.marqetonapi.dao.impl.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.marqeton.marqetonapi.dao.admin.ProductDAO;
import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductMultimedia;
import com.marqeton.marqetonapi.repository.admin.ProductDetailOptionRepository;
import com.marqeton.marqetonapi.repository.admin.ProductDetailRepository;
import com.marqeton.marqetonapi.repository.admin.ProductMultimediaRepository;
import com.marqeton.marqetonapi.repository.admin.ProductRepository;

@Repository("ProductRepository")
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductDetailRepository productDetailRepository;
	
	@Autowired
	ProductMultimediaRepository productMultimediaRepository;
	
	@Autowired
	ProductDetailOptionRepository productDetailOptionRepository;

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return new ArrayList<Product>((List<Product>) productRepository.findAll()); 
	}

	@Override
	public List<ProductDetail> getPrimaryProductDetails() {

		return productDetailRepository.findByIsPrimary(1);
	}

	@Override
	public Optional<Product> getProduct(Long id) {

		return productRepository.findById(id);
	}

	@Override
	public ProductDetail saveProductDetail(ProductDetail productDetail) {

		return productDetailRepository.save(productDetail);
	}

	@Override
	public Product getProductWithPrimaryProductDetail(Long id) {

		return productRepository.findByIdAndProductDetailListIsPrimary(id, new Integer(1));
	}
	
	@Override
	public Optional<ProductDetail> getProductDetail(Long id) {

		return productDetailRepository.findById(id);
	}
	
	@Override
	public List<ProductMultimedia> getProductDetailMultimedia(ProductDetail productDetail) {

		return productMultimediaRepository.findByProductDetail(productDetail);
	}

	@Override
	public void deleteProductDetail(ProductDetail productDetail) {
		productDetailRepository.delete(productDetail);
		
	}
	
	@Override
	public Optional<ProductMultimedia> getProductMultimedia(Long id) {

		return productMultimediaRepository.findById(id);
	}

	@Override
	public void deleteProductMultimedia(ProductMultimedia productMultimedia) {
		productMultimediaRepository.delete(productMultimedia);
	}
	
	@Override
	public void deleteProductMultimediaByProductDetail(ProductDetail productDetail) {
		productMultimediaRepository.deleteByProductDetail(productDetail);
	}

	@Override
	public List<ProductDetail> getProductDetailByProduct(Product product) {
		return productDetailRepository.findByProduct(product);
	}

	@Override
	public void deleteProductDetailOptionByProductDetail(ProductDetail productDetail) {
		productDetailOptionRepository.deleteByProductDetail(productDetail);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public int updateIsPrimaryByProduct(int isPrimary, Product product) {
		return productDetailRepository.updateIsPrimaryByProduct(isPrimary, product);
	}

}
