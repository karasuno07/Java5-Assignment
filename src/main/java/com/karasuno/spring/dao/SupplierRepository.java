package com.karasuno.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
