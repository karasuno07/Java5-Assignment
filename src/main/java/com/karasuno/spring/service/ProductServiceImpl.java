package com.karasuno.spring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.karasuno.spring.dao.CategoryRepository;
import com.karasuno.spring.dao.OriginRepository;
import com.karasuno.spring.dao.ProductRepository;
import com.karasuno.spring.dao.SupplierRepository;
import com.karasuno.spring.entity.Category;
import com.karasuno.spring.entity.Origin;
import com.karasuno.spring.entity.Product;
import com.karasuno.spring.entity.Supplier;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	private CategoryRepository categoryRepository;

	private SupplierRepository supplierRepository;

	private OriginRepository originRepository;
	
	@Autowired // this annotation is optional
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository,
			SupplierRepository supplierRepository, OriginRepository originRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.supplierRepository = supplierRepository;
		this.originRepository = originRepository;
	}

	/* Product service */

	@Override
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(int id) {
		return productRepository.getById(id);
	}

	@Override
	public Product getProduct(int id, String tag) {
		return productRepository.findByIdAndCategoryTag(id, tag);
	}

	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public List<Product> findProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Product> desiredPage = productRepository.findAll(pageable);
		return desiredPage.getContent();
	}

	@Override
	public List<Product> findProductByCategoryTag(String tag, int page, int size) {
		return productRepository.findAllByCategoryTag(tag, PageRequest.of(page, size)).getContent();
	}

	@Override
	public List<Product> findRandomProducts(int size) {
		long quantity = productRepository.count();
		size = quantity < size ? (int) quantity : size;

        Random random = new Random();
        HashSet<Product> generated = new HashSet<>();
        while (generated.size() < size) {
            int index = random.nextInt((int) quantity);
			Page<Product> page = productRepository.findAll(PageRequest.of(index, 1));
			if (page.hasContent()) {
				generated.add(page.getContent().get(0));
			}
        }

		List<Product> list = new ArrayList<>(generated);
		return list;
	}

	@Override
	public List<Product> findRelatedProducts(Product currentProduct, String tag, int size) {
		long quantity = productRepository.countByCategoryTag(tag);
		// condition: quantity <= size that when quantity > size at least 1 unit, it will not cause stackoverflow
		// minus 1 because we need to exclude one record of current product
		size = quantity <= size ? (int) quantity - 1 : size;
		
		Random random = new Random();
        HashSet<Product> generated = new HashSet<>();
        while (generated.size() < size) {
            int index = random.nextInt((int) quantity);
			Page<Product> page = productRepository.findAllByCategoryTag(tag, PageRequest.of(index, 1));
			Product product;
			if (page.hasContent() && (product = page.getContent().get(0)).getId() != currentProduct.getId()) {
				generated.add(product);
			}
        }

		List<Product> list = new ArrayList<>(generated);
		return list;
	}

	@Override
	public int countPagesByTag(String tag, int size) {
		int count = (int) (tag.equals("all") ? productRepository.count() : productRepository.countByCategoryTag(tag));
		int sizeOfPages = count < size ? 1 : (count % 2 == 0 ? count / size : count / size + 1);
		return sizeOfPages;
	}

	/* Category service */

	@Override
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(int id) {
		return categoryRepository.getById(id);
	}

	@Override
	public Category getCategorybyTag(String tag) {
		return categoryRepository.findByTag(tag);
	}

	@Override
	public void saveCategory(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}

	/* Supplier service */

	@Override
	public List<Supplier> findAllSuppliers() {
		return supplierRepository.findAll();
	}

	@Override
	public Supplier getSupplier(int id) {
		return supplierRepository.getById(id);
	}

	@Override
	public void saveSupplier(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
		supplierRepository.delete(supplier);
	}

	/* Origin service */

	@Override
	public List<Origin> findAllOrigins() {
		return originRepository.findAll();
	}

	@Override
	public Origin getOrigin(int id) {
		return originRepository.getById(id);
	}

	@Override
	public void saveOrigin(Origin origin) {
		originRepository.save(origin);
	}

	@Override
	public void deleteOrigin(Origin origin) {
		originRepository.delete(origin);
	}

}
