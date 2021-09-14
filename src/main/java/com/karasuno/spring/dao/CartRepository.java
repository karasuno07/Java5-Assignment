package com.karasuno.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

}
