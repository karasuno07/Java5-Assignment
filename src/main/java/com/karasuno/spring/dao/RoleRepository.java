package com.karasuno.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Role findByRole(String role);

}
