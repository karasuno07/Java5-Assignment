package com.karasuno.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Origin;

public interface OriginRepository extends JpaRepository<Origin, Integer> {

}
