package com.karasuno.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public Product findByIdAndCategoryTag(int id, String tag);

	public Page<Product> findAllByCategoryTag(String tag, Pageable pageable);

	public long countByCategoryTag(String tag);
}
