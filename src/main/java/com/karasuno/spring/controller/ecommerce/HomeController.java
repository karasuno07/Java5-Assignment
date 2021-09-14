package com.karasuno.spring.controller.ecommerce;

import java.util.List;

import javax.annotation.PostConstruct;

import com.karasuno.spring.entity.Category;
import com.karasuno.spring.entity.Product;
import com.karasuno.spring.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

	private final ProductService productService;

	private List<Product> products;

	private List<Category> categories;

	public HomeController(ProductService productService) {
		this.productService = productService;
	}

	@PostConstruct
	public void init() {
		products = productService.findRandomProducts(6);
		categories = productService.findAllCategories();
	}

	@GetMapping
	public String showLandingPage() {
		return "redirect:home";
	}

	@GetMapping("/home")
	public ModelAndView homepage() {
		ModelAndView mv = new ModelAndView("ecommerce/index");
		mv.addObject("products", products);
		mv.addObject("categories", categories);
		return mv;
	}

}
