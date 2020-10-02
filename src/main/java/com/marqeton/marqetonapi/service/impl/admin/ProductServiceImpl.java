package com.marqeton.marqetonapi.service.impl.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marqeton.marqetonapi.dao.admin.ProductDAO;
import com.marqeton.marqetonapi.exception.ProductDetailNotFoundException;
import com.marqeton.marqetonapi.exception.ProductMultimediaNotFoundException;
import com.marqeton.marqetonapi.exception.ProductNotFoundException;
import com.marqeton.marqetonapi.model.Product;
import com.marqeton.marqetonapi.model.ProductDetail;
import com.marqeton.marqetonapi.model.ProductMultimedia;
import com.marqeton.marqetonapi.model.ProductdetailOption;
import com.marqeton.marqetonapi.payload.ProductPayload;
import com.marqeton.marqetonapi.service.admin.ProductService;
import com.marqeton.marqetonapi.utility.FileStorage;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	FileStorage fileStorage;

	@Autowired
	private Environment env;

	@Override
	@Transactional
	public Product saveProduct(MultipartFile[] images,ProductPayload productPayload) {

		/** Product **/
		Product product = new Product();
		product.setBrand(productPayload.getBrand());
		if (productPayload.getSubCategoryList() != null && productPayload.getSubCategoryList().size() <=0) {
			product.setCategory(productPayload.getCategoryList());
		} else if (productPayload.getSubCategoryList() == null) {
			product.setCategory(productPayload.getCategoryList());
		} else {
			product.setCategory(productPayload.getSubCategoryList());
		}

		product.setStatus(productPayload.getStatus());
		product.setCreatedOn(new Date());
		product.setUpdatedOn(new Date());
		
		Product savedProduct = productDAO.saveProduct(product);

		/** Product Detail **/
		ProductDetail productDetail = new ProductDetail();
		ArrayList<ProductDetail> productDetailList = new ArrayList<ProductDetail>();
		productDetail.setName(productPayload.getName());
		productDetail.setDescription(productPayload.getDescription());
		productDetail.setSku(productPayload.getSku());
		productDetail.setStock(productPayload.getStock());
		productDetail.setActualPrice(productPayload.getActualPrice());
		productDetail.setDiscountPrice(productPayload.getDiscountPrice());
		productDetail.setProcuredPrice(productPayload.getProcuredPrice());
		productDetail.setConcerns(productPayload.getConcernList());
		productDetail.setReturnable(productPayload.getReturnable());
		productDetail.setDiscountPercentage(productPayload.getDiscountPercentage());
		productDetail.setStatus(productPayload.getStatus());
		productDetail.setCreatedOn(new Date());
		productDetail.setUpdatedOn(new Date());
		productDetail.setProduct(savedProduct);
		productDetail.setIsPrimary(1);
		productDetailList.add(productDetail);
		
		/** Product Detail Option **/
		for(ProductdetailOption productDetailOption: productPayload.getProductDetailOptionList()) {
			productDetailOption.setProductDetail(productDetail);
		}
		productDetail.setProductdetailOptionList(productPayload.getProductDetailOptionList());


		/** Product Multimedia **/
		ProductMultimedia productMultimedia = null;
		ArrayList<ProductMultimedia> productMultiMediaList = new ArrayList<ProductMultimedia>();
		String relativePath = fileStorage.PRODUCT_DIR+productDetail.getSku()+"/";
		fileStorage.createDirectory(relativePath);
		fileStorage.deleteAll(relativePath);
		String filePattern = new String();
		int count=0;
		for(MultipartFile imageFile: images) {
			filePattern = fileStorage.getFilePattern();
			fileStorage.store(imageFile,productDetail.getSku()+filePattern,relativePath);
			String domainName = env.getProperty("app.domainName");
			String productServerDir = env.getProperty("app.productServerDir");
			String productUrl = domainName+productServerDir+productDetail.getSku()+"/"+productDetail.getSku()+filePattern+"."+FilenameUtils.getExtension(imageFile.getOriginalFilename());
			productMultimedia = new ProductMultimedia();
			productMultimedia.setImage(productUrl);
			if(count==0) {
				productMultimedia.setIsPrimary(1);
			}
			productMultimedia.setCreatedOn(new Date());
			productMultimedia.setUpdatedOn(new Date());
			productMultimedia.setProductDetail(productDetail);
			++count;
			productMultiMediaList.add(productMultimedia);
		}
		
		productDetail.setMultimediaList(productMultiMediaList);
		//savedProduct.setPrimaryProductDetail(productDetail);
		savedProduct.setProductDetailList(productDetailList);
		
		Product updatedProduct = productDAO.saveProduct(savedProduct);

		return updatedProduct;
	}

	@Override
	@Transactional
	public List<ProductPayload> getProducts(Boolean isPrimary) {
		if(isPrimary) {
			List<ProductDetail> primaryProductDetail =  productDAO.getPrimaryProductDetails();
			List<ProductPayload> primaryProductPayload = createPrimaryProductPayload(primaryProductDetail);
			return primaryProductPayload;
		}
		else {
			return createProductPayload(productDAO.getProducts());
		}
	}

	private List<ProductPayload> createPrimaryProductPayload(List<ProductDetail> primaryProductDetail) {
		
		List<ProductPayload> productPayloadList = new ArrayList<ProductPayload>();
		ProductPayload productPayload = null;
		for(ProductDetail pd: primaryProductDetail) {
			productPayload = new ProductPayload();
			productPayload.setId(pd.getProduct().getId());
			productPayload.setName(pd.getName());
			productPayload.setDescription(pd.getDescription());
			productPayload.setDiscountPercentage(pd.getDiscountPercentage());
			productPayload.setReturnable(pd.isReturnable());
			productPayload.setSku(pd.getSku());
			productPayload.setActualPrice(pd.getActualPrice());
			productPayload.setProcuredPrice(pd.getProcuredPrice());
			productPayload.setDiscountPrice(pd.getDiscountPrice());
			productPayload.setStock(pd.getStock());
			productPayload.setStatus(pd.getStatus());
			productPayload.setBrand(pd.getProduct().getBrand());
			productPayload.setCategoryList(pd.getProduct().getCategory().stream().filter(cat -> cat.getCategory() == null).collect(Collectors.toList()));
			productPayload.setSubCategoryList(pd.getProduct().getCategory().stream().filter(cat -> cat.getCategory() != null).collect(Collectors.toList()));
			// Add sub category's parent category also in category
						productPayload.getCategoryList().addAll(productPayload.getSubCategoryList().stream().map(sc -> sc.getCategory()).collect(Collectors.toList()));
			productPayload.setProductDetailOptionList(pd.getProductdetailOptionList());
			productPayload.setConcernList(pd.getConcerns());
			productPayload.setProductMultimediaList(pd.getMultimediaList());
			productPayloadList.add(productPayload);
		}
		return productPayloadList;
	}
	
	private List<ProductPayload> createProductPayload(List<Product> productList) {
		List<ProductPayload> productPayloadList = new ArrayList<ProductPayload>();
		ProductPayload productPayload = null;
		for(Product p: productList) {
			productPayload = new ProductPayload();
			List<ProductDetail> pdList = p.getProductDetailList().stream().filter(productDetail -> productDetail.getIsPrimary().equals(new Integer(1))).collect(Collectors.toList());
			ProductDetail pd = pdList.get(0);
			productPayload.setId(p.getId());
			productPayload.setName(pd.getName());
			productPayload.setDescription(pd.getDescription());
			productPayload.setDiscountPercentage(pd.getDiscountPercentage());
			productPayload.setReturnable(pd.isReturnable());
			productPayload.setSku(pd.getSku());
			productPayload.setActualPrice(pd.getActualPrice());
			productPayload.setProcuredPrice(pd.getProcuredPrice());
			productPayload.setDiscountPrice(pd.getDiscountPrice());
			productPayload.setStock(pd.getStock());
			productPayload.setStatus(pd.getStatus());
			productPayload.setBrand(pd.getProduct().getBrand());
			productPayload.setCategoryList(pd.getProduct().getCategory());
			productPayload.setSubCategoryList(pd.getProduct().getCategory().stream().filter(cat -> cat.getCategory() != null).collect(Collectors.toList()));
			productPayload.setProductDetailOptionList(pd.getProductdetailOptionList());
			productPayload.setConcernList(pd.getConcerns());
			productPayload.setProductVarients(p.getProductDetailList());
			productPayload.setProductMultimediaList(pd.getMultimediaList());
			productPayloadList.add(productPayload);
		}
		return productPayloadList;
	}

	@Override
	@Transactional
	public ProductDetail saveProductDetail(MultipartFile[] images, ProductPayload productPayload, Long id) {
		Optional<Product> product = null;
		ProductDetail productDetail = null;
		ProductDetail savedProductDetail = null;
		
		product = productDAO.getProduct(id);
		if(product != null && product.isPresent()) {
			productDetail = new ProductDetail();
			productDetail.setName(productPayload.getName());
			productDetail.setDescription(productPayload.getDescription());
			productDetail.setSku(productPayload.getSku());
			productDetail.setStock(productPayload.getStock());
			productDetail.setActualPrice(productPayload.getActualPrice());
			productDetail.setDiscountPrice(productPayload.getDiscountPrice());
			productDetail.setProcuredPrice(productPayload.getProcuredPrice());
			productDetail.setConcerns(productPayload.getConcernList());
			productDetail.setReturnable(productPayload.getReturnable());
			productDetail.setDiscountPercentage(productPayload.getDiscountPercentage());
			productDetail.setStatus(productPayload.getStatus());
			productDetail.setCreatedOn(new Date());
			productDetail.setUpdatedOn(new Date());
			productDetail.setProduct(product.get());
			productDetail.setIsPrimary(0);
			
			/** Product Detail Option **/
			for(ProductdetailOption productDetailOption: productPayload.getProductDetailOptionList()) {
				productDetailOption.setProductDetail(productDetail);
			}
			productDetail.setProductdetailOptionList(productPayload.getProductDetailOptionList());
			
			/** Product Multimedia **/
			ProductMultimedia productMultimedia = null;
			ArrayList<ProductMultimedia> productMultiMediaList = new ArrayList<ProductMultimedia>();
			String relativePath = fileStorage.PRODUCT_DIR+productDetail.getSku()+"/";
			fileStorage.createDirectory(relativePath);
			fileStorage.deleteAll(relativePath);
			int count=0;
			for(MultipartFile imageFile: images) {
				String filePattern = fileStorage.getFilePattern();
				fileStorage.store(imageFile,productDetail.getSku()+filePattern,relativePath);
				String domainName = env.getProperty("app.domainName");
				String productServerDir = env.getProperty("app.productServerDir");
				String productUrl = domainName+productServerDir+productDetail.getSku()+"/"+productDetail.getSku()+filePattern+"."+FilenameUtils.getExtension(imageFile.getOriginalFilename());
				productMultimedia = new ProductMultimedia();
				productMultimedia.setImage(productUrl);
				if(count==0) {
					productMultimedia.setIsPrimary(1);
				}
				productMultimedia.setCreatedOn(new Date());
				productMultimedia.setUpdatedOn(new Date());
				productMultimedia.setProductDetail(productDetail);
				++count;
				productMultiMediaList.add(productMultimedia);
			}
			
			productDetail.setMultimediaList(productMultiMediaList);
			savedProductDetail = productDAO.saveProductDetail(productDetail);
		}
		return savedProductDetail;
	}

	@Override
	@Transactional
	public Product updateProduct(MultipartFile[] images, ProductPayload productPayload, Long id) {
		
		/** Product **/
		Product existingProduct = productDAO.getProductWithPrimaryProductDetail(id);
		existingProduct.setBrand(productPayload.getBrand());
		if (productPayload.getSubCategoryList() != null) {
			if (productPayload.getSubCategoryList().size() <=0) {
				existingProduct.setCategory(productPayload.getCategoryList());
			}
		} else if (productPayload.getSubCategoryList() == null) {
			existingProduct.setCategory(productPayload.getCategoryList());
		} else {
			existingProduct.setCategory(productPayload.getSubCategoryList());
		}

		existingProduct.setStatus(productPayload.getStatus());

		/** Product Detail **/
		ProductDetail productDetail = existingProduct.getProductDetailList().get(0);
		ArrayList<ProductDetail> productDetailList = new ArrayList<ProductDetail>();
		productDetail.setName(productPayload.getName());
		productDetail.setDescription(productPayload.getDescription());
		productDetail.setSku(productPayload.getSku());
		productDetail.setStock(productPayload.getStock());
		productDetail.setActualPrice(productPayload.getActualPrice());
		productDetail.setDiscountPrice(productPayload.getDiscountPrice());
		productDetail.setProcuredPrice(productPayload.getProcuredPrice());
		productDetail.setConcerns(productPayload.getConcernList());
		productDetail.setReturnable(productPayload.getReturnable());
		productDetail.setDiscountPercentage(productPayload.getDiscountPercentage());
		productDetail.setStatus(productPayload.getStatus());
		productDetail.setCreatedOn(new Date());
		productDetail.setUpdatedOn(new Date());
		productDetail.setProduct(existingProduct);
		productDetail.setIsPrimary(productPayload.getStatus());
		productDetailList.add(productDetail);
		
		/** Product Detail Option **/
		for(ProductdetailOption productDetailOption: productPayload.getProductDetailOptionList()) {
			productDetailOption.setProductDetail(productDetail);
		}
		productDetail.setProductdetailOptionList(productPayload.getProductDetailOptionList());


		/** Product Multimedia **/
		if(images != null) {
			ProductMultimedia productMultimedia = null;
			ArrayList<ProductMultimedia> productMultiMediaList = new ArrayList<ProductMultimedia>();
			String relativePath = fileStorage.PRODUCT_DIR+productDetail.getSku()+"/";
			fileStorage.createDirectory(relativePath);
			//fileStorage.deleteAll(relativePath);
			String filePattern = fileStorage.getFilePattern();
			int count=0;
			for(MultipartFile imageFile: images) {
				fileStorage.store(imageFile,productDetail.getSku()+filePattern,relativePath);
				String domainName = env.getProperty("app.domainName");
				String productServerDir = env.getProperty("app.productServerDir");
				String productUrl = domainName+productServerDir+productDetail.getSku()+"/"+productDetail.getSku()+filePattern+"."+FilenameUtils.getExtension(imageFile.getOriginalFilename());
				productMultimedia = new ProductMultimedia();
				productMultimedia.setImage(productUrl);
				if(count==0) {
					productMultimedia.setIsPrimary(1);
				}
				productMultimedia.setCreatedOn(new Date());
				productMultimedia.setUpdatedOn(new Date());
				productMultimedia.setProductDetail(productDetail);
				++count;
				productMultiMediaList.add(productMultimedia);
			}

			productDetail.setMultimediaList(productMultiMediaList);
		}
		//savedProduct.setPrimaryProductDetail(productDetail);
		existingProduct.setProductDetailList(productDetailList);
		
		Product updatedProduct = productDAO.saveProduct(existingProduct);

		return updatedProduct;
	}

	@Override
	@Transactional
	public void deleteProductDetail(Long prodId, Long detailId) {
		List<ProductMultimedia> productMultimediaList = null;
		// Find product Detail Object
		Optional<ProductDetail> productDetail = productDAO.getProductDetail(detailId);
		
		//If productDetail is not null get multi media list to delete it before deleting table row
		if(productDetail.isPresent()) {
			productMultimediaList = productDAO.getProductDetailMultimedia(productDetail.get());
			
			if(productMultimediaList != null) {
				String relativeDirectoryPath = fileStorage.PRODUCT_DIR+productDetail.get().getSku();
				fileStorage.delete(relativeDirectoryPath);
			}
			
			productDAO.deleteProductDetail(productDetail.get());
		}
		else {
			throw new ProductDetailNotFoundException("ProductDetail not found - " + detailId );
		}
	
	}

	@Override
	@Transactional
	public void deleteProductMultimedia(Long prodId, Long detailId, Long multiId) {
		Optional<ProductMultimedia> productMultimedia = productDAO.getProductMultimedia(multiId);
		
		if(productMultimedia.isPresent()) {
			if( productMultimedia.get().getImage() != null ) {
				String relativePath = fileStorage.PRODUCT_DIR+productMultimedia.get().getImage().substring((env.getProperty("app.domainName")+env.getProperty("app.productServerDir")).length());
				fileStorage.delete(relativePath);
			}
			
			productDAO.deleteProductMultimedia(productMultimedia.get());
		} else {
			throw new ProductMultimediaNotFoundException("Product Multimedia not found - " + multiId);
		}
	}

	@Override
	public List<ProductPayload> getProductDetails(Long id) {
		Optional<Product> product = productDAO.getProduct(id);
		if(product.isPresent()) {
			List<ProductDetail> productDetailList = productDAO.getProductDetailByProduct(product.get());
			return createProductPayloadByProductDetailList(productDetailList);
		}
		else {
			throw new ProductNotFoundException("Product with id " + id + " not found");
		}
		
	}

	private List<ProductPayload> createProductPayloadByProductDetailList(List<ProductDetail> productDetailList) {
		List<ProductPayload> productPayloadList = new ArrayList<ProductPayload>();
		ProductPayload productPayload = null;
		for(ProductDetail pd: productDetailList) {
			productPayload = new ProductPayload();
			productPayload.setId(pd.getProduct().getId());
			productPayload.setProductDetailId(pd.getId());
			productPayload.setName(pd.getName());
			productPayload.setDescription(pd.getDescription());
			productPayload.setDiscountPercentage(pd.getDiscountPercentage());
			productPayload.setReturnable(pd.isReturnable());
			productPayload.setSku(pd.getSku());
			productPayload.setActualPrice(pd.getActualPrice());
			productPayload.setProcuredPrice(pd.getProcuredPrice());
			productPayload.setDiscountPrice(pd.getDiscountPrice());
			productPayload.setStock(pd.getStock());
			productPayload.setStatus(pd.getStatus());
			productPayload.setIsPrimary(pd.getIsPrimary());
			productPayload.setBrand(pd.getProduct().getBrand());
			productPayload.setCategoryList(pd.getProduct().getCategory().stream().filter(cat -> cat.getCategory() == null).collect(Collectors.toList()));
			productPayload.setSubCategoryList(pd.getProduct().getCategory().stream().filter(cat -> cat.getCategory() != null).collect(Collectors.toList()));
			// Add sub category's parent category also in category
			productPayload.getCategoryList().addAll(productPayload.getSubCategoryList().stream().map(sc -> sc.getCategory()).collect(Collectors.toList()));
			productPayload.setProductDetailOptionList(pd.getProductdetailOptionList());
			productPayload.setOptionList(pd.getProductdetailOptionList().stream().map(pdo -> pdo.getOption()).collect(Collectors.toList()));
			productPayload.setConcernList(pd.getConcerns());
			productPayload.setProductMultimediaList(pd.getMultimediaList());
			productPayloadList.add(productPayload);
		}
		return productPayloadList;
	}

	@Override
	@Transactional
	public ProductPayload updateProductDetail(MultipartFile[] images, ProductPayload productPayload, Long prodId,
			Long detailId) {
		/** Get the product Detail mentioned in UI **/
		ProductDetail existingProductDetail = productDAO.getProductDetail(detailId).orElseThrow(ProductDetailNotFoundException::new);
		Product existingProduct = existingProductDetail.getProduct();
		String oldSku = existingProductDetail.getSku();
		existingProduct.setBrand(productPayload.getBrand());
		if (productPayload.getSubCategoryList() != null && productPayload.getSubCategoryList().size() <=0) {
			existingProduct.setCategory(productPayload.getCategoryList());
		} else if (productPayload.getSubCategoryList() == null) {
			existingProduct.setCategory(productPayload.getCategoryList());
		} else {
			existingProduct.setCategory(productPayload.getSubCategoryList());
		}

		existingProduct.setStatus(productPayload.getStatus());

		productDAO.deleteProductMultimediaByProductDetail(existingProductDetail);
		productDAO.deleteProductDetailOptionByProductDetail(existingProductDetail);
		if(productPayload.getIsPrimary().equals(1)) {
			productDAO.updateIsPrimaryByProduct(0, existingProduct);
		}
		/** Product Detail **/
		ArrayList<ProductDetail> productDetailList = new ArrayList<ProductDetail>();
		existingProductDetail.setName(productPayload.getName());
		existingProductDetail.setDescription(productPayload.getDescription());
		existingProductDetail.setSku(productPayload.getSku());
		existingProductDetail.setStock(productPayload.getStock());
		existingProductDetail.setActualPrice(productPayload.getActualPrice());
		existingProductDetail.setDiscountPrice(productPayload.getDiscountPrice());
		existingProductDetail.setProcuredPrice(productPayload.getProcuredPrice());
		existingProductDetail.setConcerns(productPayload.getConcernList());
		existingProductDetail.setReturnable(productPayload.getReturnable());
		existingProductDetail.setDiscountPercentage(productPayload.getDiscountPercentage());
		existingProductDetail.setStatus(productPayload.getStatus());
		existingProductDetail.setCreatedOn(new Date());
		existingProductDetail.setUpdatedOn(new Date());
		existingProductDetail.setProduct(existingProduct);
		existingProductDetail.setIsPrimary(productPayload.getIsPrimary());
		productDetailList.add(existingProductDetail);
		
		/** Product Detail Option **/
		for(ProductdetailOption productDetailOption: productPayload.getProductDetailOptionList()) {
			productDetailOption.setProductDetail(existingProductDetail);
		}
		existingProductDetail.setProductdetailOptionList(productPayload.getProductDetailOptionList());

		
		/** Product Multimedia **/
		ArrayList<ProductMultimedia> productMultiMediaList = new ArrayList<ProductMultimedia>();
		// Delete image file other than the ones present in the list
		String directoryRelativePath = fileStorage.PRODUCT_DIR+existingProductDetail.getSku()+"/";
		List<String> fileRelativePaths = productPayload.getProductMultimediaList().stream().map(pm -> directoryRelativePath+pm.getImage().substring((env.getProperty("app.domainName")+env.getProperty("app.productServerDir")+oldSku+"/").length())).collect(Collectors.toList());
		
		if(!oldSku.equalsIgnoreCase(existingProductDetail.getSku())) {
			String oldDirectoryRelativePath = fileStorage.PRODUCT_DIR+oldSku+"/";
			
			try {
				fileStorage.renameDirectory(oldDirectoryRelativePath, directoryRelativePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		fileStorage.deleteAllExcept(directoryRelativePath, fileRelativePaths);
		productMultiMediaList.addAll(productPayload.getProductMultimediaList());
		if(images != null) {
			ProductMultimedia productMultimedia = null;
			
			String relativePath = fileStorage.PRODUCT_DIR+existingProductDetail.getSku()+"/";
			fileStorage.createDirectory(relativePath);
			String filePattern = new String();
			for(MultipartFile imageFile: images) {
				filePattern = fileStorage.getFilePattern();
				fileStorage.store(imageFile,existingProductDetail.getSku()+filePattern,relativePath);
				String domainName = env.getProperty("app.domainName");
				String productServerDir = env.getProperty("app.productServerDir");
				String productUrl = domainName+productServerDir+existingProductDetail.getSku()+"/"+existingProductDetail.getSku()+filePattern+"."+FilenameUtils.getExtension(imageFile.getOriginalFilename());
				productMultimedia = new ProductMultimedia();
				productMultimedia.setImage(productUrl);
				productMultimedia.setCreatedOn(new Date());
				productMultimedia.setUpdatedOn(new Date());
				productMultimedia.setProductDetail(existingProductDetail);
				productMultiMediaList.add(productMultimedia);
			}
			
			existingProductDetail.setMultimediaList(productMultiMediaList);
			
		}
		productPayload.getProductMultimediaList().forEach(pm -> pm.setProductDetail(existingProductDetail));
		
		existingProduct.setProductDetailList(productDetailList);
		
		Product updatedProduct = productDAO.saveProduct(existingProduct);
		
		// Set the ID of the updated product
		productPayload.setId(updatedProduct.getId());
		return productPayload;
	}

	@Override
	public void deleteProduct(Long prodId) {
		List<ProductMultimedia> productMultimediaList = null;
		
		Optional<Product> product = productDAO.getProduct(prodId);
		if(product.isPresent()) {
			List<ProductDetail> productDetailList = productDAO.getProductDetailByProduct(product.get());
			
			for(ProductDetail productDetail: productDetailList) {
				productMultimediaList = productDAO.getProductDetailMultimedia(productDetail);
			
				if(productMultimediaList != null) {
					for(ProductMultimedia productMultimedia: productMultimediaList) {
						if( productMultimedia.getImage() != null ) {
							String relativePath = fileStorage.PRODUCT_DIR+productMultimedia.getImage().substring((env.getProperty("app.domainName")+env.getProperty("app.productServerDir")).length());
							fileStorage.delete(relativePath);
						}
					}
				}
			
				//productDAO.deleteProductDetail(productDetail);
			}
			
			productDAO.deleteProduct(product.get());
		}
		else {
			throw new ProductNotFoundException("Product with id " + prodId + " not found");
		}
		
	}

}
