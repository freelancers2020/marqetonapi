package com.marqeton.marqetonapi.constants;
/**
* <h1>RestUrlConstants</h1>
* The RestUrlConstants is used for the API end points constants. 
* So that URL changed here will change in the entire application.
* <p>
*  <b>/api/cust/ **</b> - is for URLs which can be accessed by CUSTOMER and ADMIN roles
*  <b>/api/admin/ **</b> - is for URLs which can be accessed only by ADMIN roles
* 
* 
*
* @author  Amith Srikanth, Ambrish Kumar Sharma
* @version 1.0
* @since   2020-02-23 
*/
public class RestUrlConstants {
	
	/*Created API for Admin Login Component*/
	public static final String ADMIN_URIS = "/api/admin/**";
	public static final String ADMIN_LOGIN = "/api/admin/public/login";
	public static final String PUBLIC_ADMIN_URIS = "/api/admin/public/**";
	public static final String PRIVATE_ADMIN_URIS = "/api/admin/private/**";
	
	/*Created API for Customer Login Component*/
	public static final String CUST_URIS = "/api/cust/**";
	public static final String CUST_LOGIN = "/api/cust/public/login";
	public static final String PUBLIC_CUST_URIS = "/api/cust/public/**";
	public static final String PRIVATE_CUST_URIS = "/api/cust/private/**";
	public static final String CUST_USERS = "/api/cust/public/users";
	
	
	/*Created API for Brand Component*/
	public static final String ADMIN_BRANDS = "/api/admin/private/brands";
	public static final String ADMIN_BRAND = "/api/admin/private/brands/{id}";
	
	/*Created API for Category Component*/
	public static final String ADMIN_CATEGORIES = "/api/admin/private/categories";
	public static final String ADMIN_CATEGORY = "/api/admin/private/categories/{id}";
	
	/*Created API for Sub Category Component*/
	public static final String ADMIN_SUB_CATEGORIES = "/api/admin/private/subcategories";
	public static final String ADMIN_SUB_CATEGORY = "/api/admin/private/subcategories/{id}";
	public static final String ADMIN_CATEGORY_SUB_CATEGORIES = "/api/admin/private/categories/{categoryId}/subcategories";
	public static final String ADMIN_CATEGORIES_SUB_CATEGORIES = "/api/admin/private/categories/subcategories";
	
	/*Created API for Product Component*/
	public static final String ADMIN_PRODUCTS = "/api/admin/private/products";
	public static final String ADMIN_PRODUCT = "/api/admin/private/products/{id}";
	public static final String ADMIN_PRODUCT_PRODUCTDETAILS = "/api/admin/private/products/{id}/product-detail";
	public static final String ADMIN_PRODUCT_PRODUCTDETAIL = "/api/admin/private/products/{prodId}/product-detail/{detailId}";
	public static final String ADMIN_PRODUCT_PRODUCTDETAIL_MULTIMEDIA = "/api/admin/private/products/{prodId}/product-detail/{detailId}/multimedia/{multiId}";
	
	/*Created API for Concern Component*/
	public static final String ADMIN_CONCERNS = "/api/admin/private/concerns";
	public static final String ADMIN_CONCERN = "/api/admin/private/concerns/{id}";
	
	/*Created API for Concern Component*/
	public static final String ADMIN_OPTIONS = "/api/admin/private/options";
	public static final String ADMIN_OPTION = "/api/admin/private/options/{id}";
	
	/*Created API for Brand Component*/
	public static final String ADMIN_BANNERS = "/api/admin/private/banner";
	public static final String ADMIN_BANNER = "/api/admin/private/banner/{id}";
	
//	public static final String NORMAL_VERIFY = "/api/public/sign-up-normal/verify/{email}";
//	public static final String FACEBOOK_SIGN_UP = "/api/public/sign-up-facebook";
//	public static final String GMAIL_SIGN_UP = "/api/public/sign-up-gmail";
//	public static final String CATEGORIES = "/api/public/categories";
//	public static final String TASKS = "/api/public/tasks";
//	public static final String BADGES = "/api/public/badges";
//	public static final String COUNTRIES = "/api/public/countries";
//	public static final String REGIONS = "/api/public/regions/{country}";
//	public static final String CITIES = "/api/public/cities/{country}/{region}";

}
