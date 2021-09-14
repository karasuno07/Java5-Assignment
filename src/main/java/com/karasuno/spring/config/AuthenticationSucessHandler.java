package com.karasuno.spring.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.karasuno.spring.entity.Account;
import com.karasuno.spring.service.AccountService;

@Component
public class AuthenticationSucessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private AccountService accountService;


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		String username = authentication.getName();

		Account loginedInUser = accountService.findByUsername(username);
		HttpSession session = request.getSession();
		session.setAttribute("user", loginedInUser);
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			String role = grantedAuthority.toString();
			if (role.equals("ROLE_ADMIN") || role.equals("ROLE_MANAGER")) {
				response.sendRedirect(request.getContextPath() + "/system");
				return;
			}
		}
			
		response.sendRedirect(request.getContextPath() + "/home");
	}

}
