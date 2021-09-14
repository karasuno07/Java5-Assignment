package com.karasuno.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.karasuno.spring.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthenticationSucessHandler sucessHandler;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/images/**").permitAll()
				.antMatchers("/assets/**").permitAll()
				.antMatchers("/registration/**").permitAll()
				.antMatchers("/system/**").hasAnyRole("ADMIN", "MANAGER")
				.anyRequest().permitAll() // sửa dòng này
				// .anyRequest().authenticated() // thành dòng này
			.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate")
				.successHandler(sucessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(accountService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
}
