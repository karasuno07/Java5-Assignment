package com.karasuno.spring.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.karasuno.spring.entity.Account;
import com.karasuno.spring.entity.Role;

public interface AccountService extends UserDetailsService {
	
	/* Account Service start */
	
	public Account findById(int id);
	
	public Account findByUsername(String username);
	
	public List<Account> findAllAccounts();
	
	public void saveAccount(Account account);
	
	public void deleteAccount(Account account);
	
	/* Account Service end */
	
	
	/* Role Service start */
	
	public Role findByRole(String roleName);
	
	public List<Role> findAllRoles();
	
	/* Role Service end */
}
