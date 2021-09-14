package com.karasuno.spring.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.karasuno.spring.entity.Account;
import com.karasuno.spring.service.AccountService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private AccountService accountService;

	@GetMapping
	public ModelAndView registrationForm() {
		ModelAndView mv = new ModelAndView("auth/registration");
		mv.addObject("user", new Account());
		return mv;
	}

	@GetMapping(value = "/isUnique/{username}", produces = "application/json")
	public @ResponseBody boolean checkUniqueUsername(@PathVariable String username) {
		Account account;
		try {
			account = accountService.findByUsername(username);
		} catch (Exception e) {
			account = null;
		}
		return account != null;
	}

	@PostMapping(value = "/check")
	public @ResponseBody Account check(@Valid @RequestBody Account user, BindingResult result) {
		System.out.println(user);
		if (result.hasErrors())
			return null;
		return user;
	}

	@PostMapping
	public ModelAndView saveAccount(@Valid @ModelAttribute("user") Account user, BindingResult result) {
		ModelAndView mv = new ModelAndView();

		if (result.hasErrors()) {
			mv.setViewName("registration");
		} else {
			accountService.saveAccount(user);
			mv.setViewName("registration-succeed");
		}
		return mv;
	}

}
