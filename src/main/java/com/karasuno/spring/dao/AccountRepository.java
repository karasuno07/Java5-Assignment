package com.karasuno.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karasuno.spring.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByUsername(String username);
}
