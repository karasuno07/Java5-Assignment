package com.karasuno.spring.controller.admin;

import java.util.Arrays;
import java.util.List;

import com.karasuno.spring.entity.Account;
import com.karasuno.spring.entity.Category;
import com.karasuno.spring.entity.Origin;
import com.karasuno.spring.entity.Product;
import com.karasuno.spring.entity.Supplier;
import com.karasuno.spring.service.AccountService;
import com.karasuno.spring.service.ProductService;
import com.karasuno.spring.view.ManageView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/system")
public class AdminController {

	private ProductService productService;

	private AccountService accountService;

	@Autowired
	public AdminController(ProductService productService, AccountService accountService) {
		this.productService = productService;
		this.accountService = accountService;
	}

	private final String VIEW = "admin/index";

	@GetMapping(value = { "", "/dashboard" })
	public ModelAndView dashboard() {
		ModelAndView mv = getModelAndView();
		mv.addObject("page", "dashboard");
		return mv;
	}

	@GetMapping(value = { "/manage/{page}" })
	public ModelAndView manageView(@PathVariable(required = false) String page) {
		ModelAndView mv = getModelAndView();

		boolean exists = Arrays.stream(ManageView.class.getEnumConstants())
				.anyMatch(view -> view.getViewName().equals(page));

		if (!exists) {
			throw new ResourceNotFoundException();
		}

		StringBuilder url = new StringBuilder("manage-");

		switch (page) {
			case "account":
				manageAccount(mv);
				break;
			case "product":
				manageProduct(mv);
				break;
		}

		mv.addObject("page", url.append(page));

		return mv;
	}

	private ModelAndView getModelAndView() {
		ModelAndView mv = new ModelAndView(VIEW);
		return mv;
	}

	private void manageAccount(ModelAndView mv) {
		List<Account> accounts = accountService.findAllAccounts();
		mv.addObject("accounts", accounts);
	}

	private void manageProduct(ModelAndView mv) {
		List<Product> products = productService.findAllProducts();
		List<Category> categories = productService.findAllCategories();
		List<Supplier> suppliers = productService.findAllSuppliers();
		List<Origin> origins = productService.findAllOrigins();
		mv.addObject("products", products);
		mv.addObject("categories", categories);
		mv.addObject("suppliers", suppliers);
		mv.addObject("origins", origins);
	}

}
