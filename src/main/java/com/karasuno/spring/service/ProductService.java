package com.karasuno.spring.service;

import java.util.List;

import com.karasuno.spring.entity.Category;
import com.karasuno.spring.entity.Origin;
import com.karasuno.spring.entity.Product;
import com.karasuno.spring.entity.Supplier;

public interface ProductService {
	
	/* Product service start */
	
	public List<Product> findAllProducts();
	
	public Product getProduct(int id);

	public Product getProduct(int id, String tag);
	
	public void saveProduct(Product product);
	
	public void deleteProduct(Product product);

	public List<Product> findProducts(int page, int size);
	
	public List<Product> findProductByCategoryTag(String tag, int page, int size);
	
	public List<Product> findRandomProducts(int size);
	
	public List<Product> findRelatedProducts(Product currentProduct, String tag, int size);

	public int countPagesByTag(String tag, int size);

	/* Product service end */
	
	
	/* Category service start */
	
	public List<Category> findAllCategories();
	
	public Category getCategory(int id);

	public Category getCategorybyTag(String tag);
	
	public void saveCategory(Category category);
	
	public void deleteCategory(Category category);
	
	/* Category service end */
	
	
	/* Supplier service start */
	
	public List<Supplier> findAllSuppliers();
	
	public Supplier getSupplier(int id);
	
	public void saveSupplier(Supplier supplier);
	
	public void deleteSupplier(Supplier supplier);
	
	/* Supplier service end */
	
	
	/* Origin service start */
	
	public List<Origin> findAllOrigins();
	
	public Origin getOrigin(int id);
	
	public void saveOrigin(Origin origin);
	
	public void deleteOrigin(Origin origin);
	
	/* Origin service start */
}	


