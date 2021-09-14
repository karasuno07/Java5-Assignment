package com.karasuno.spring.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.karasuno.spring.dao.AccountRepository;
import com.karasuno.spring.dao.RoleRepository;
import com.karasuno.spring.entity.Account;
import com.karasuno.spring.entity.Role;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository, RoleRepository roleRepository) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account user;
		try {
			user =	findByUsername(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("Did not find username: " + username);
		}
		
		return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	
	
	@Override
	public Account findById(int id) {
		Optional<Account> result = accountRepository.findById(id);
		Account account = null;
		if (result.isPresent()) {
			account = result.get();
		} else {
			throw new EntityNotFoundException("Did not find user with id: " + id);
		}
		return account;
	}

	@Override
	public Account findByUsername(String username) {
		Account account = accountRepository.findByUsername(username);
		return account;
	}

	@Override
	public List<Account> findAllAccounts() {
		List<Account> list = accountRepository.findAll();
		return list;
	}

	@Override
	public void saveAccount(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		if (account.getRoles() == null || account.getRoles().size() == 0) {
			account.addRole(findByRole("ROLE_CUSTOMER"));
		}
		accountRepository.save(account);
	}

	@Override
	public void deleteAccount(Account account) {
		accountRepository.delete(account);
	}

	@Override
	public Role findByRole(String roleName) {
		return roleRepository.findByRole(roleName);
	}

	@Override
	public List<Role> findAllRoles() {
		List<Role> list = roleRepository.findAll();
		return list;
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

}
