package com.karasuno.spring.controller.ecommerce;

import java.util.List;

import javax.annotation.PostConstruct;

import com.karasuno.spring.entity.Category;
import com.karasuno.spring.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class CartController {
    
    @Autowired
    private ProductService productService;

    private List<Category> categories;

    @PostConstruct
    public void init() {
        categories = productService.findAllCategories();
    }

    @GetMapping
    public ModelAndView showCart() {
        ModelAndView mv = getModelAndView();
        return mv;
    }

    private ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("ecommerce/pages/cart");
        mv.addObject("categories", categories);
        return mv;
    }
}