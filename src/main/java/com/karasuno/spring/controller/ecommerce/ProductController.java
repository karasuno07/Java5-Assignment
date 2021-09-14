package com.karasuno.spring.controller.ecommerce;

import java.util.List;

import javax.annotation.PostConstruct;

import com.karasuno.spring.entity.Category;
import com.karasuno.spring.entity.Product;
import com.karasuno.spring.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    private int size;
    private List<Product> products;
    private List<Product> relatives;
    private List<Category> categories;

    @PostConstruct
    public void init() {
        size = 6;
        categories = productService.findAllCategories();
    }

    @GetMapping
    public String defaultRedirect() {
        return "redirect:/product/all";
    }

    @GetMapping(value = "/{tag}")
    public ModelAndView listProductView(@PathVariable String tag, @RequestParam(required = false) Integer page) {
        ModelAndView mv = new ModelAndView("ecommerce/pages/product-list");

        boolean exists = categories.stream().anyMatch(c -> c.getTag().equals(tag)); // check if the tag is existing

        if ((!exists && !tag.equals("all")) || (page != null && page < 1)) { // throw 404 exception if tag doesn't exist or invalid page number
            throw new ResourceNotFoundException();
        }

        Category selectedCategory = null;
        page = page == null || page == 1 ? 0 : page - 1;

        if (tag.equals("all")) { // fill products to list based on the tag
            products = productService.findProducts(page, size);
        } else {
            products = productService.findProductByCategoryTag(tag, page, size);
            selectedCategory = productService.getCategorybyTag(tag); // get selected category
        }

        int sizeOfPages = productService.countPagesByTag(tag, 6);
        mv.addObject("categoryName", selectedCategory != null ? selectedCategory.getName() : "All Category");
        mv.addObject("tag", selectedCategory != null ? selectedCategory.getTag() : "all");
        mv.addObject("categories", categories);
        mv.addObject("products", products);
        mv.addObject("sizeOfPages", sizeOfPages);

        return mv;
    }

    @GetMapping(value = "/{tag}/{id}")
    public ModelAndView singleProductView(@PathVariable String tag, @PathVariable int id) {
        ModelAndView mv = new ModelAndView("ecommerce/pages/single-product");

        boolean exists = categories.stream().anyMatch(c -> c.getTag().equals(tag)); // check if the tag is existing

        if ((!exists && !tag.equals("all"))) { // throw 404 exception if tag doesn't exist or invalid page number
            throw new ResourceNotFoundException();
        }

        Product product = tag.equals("all") ? productService.getProduct(id) : productService.getProduct(id, tag);

        if (product == null) { // throw 404 exception if doesn't find the product
            throw new ResourceNotFoundException();
        }

        relatives = productService.findRelatedProducts(product, tag, 3);

        mv.addObject("categories", categories);
        mv.addObject("product", product);
        mv.addObject("relatives", relatives);

        return mv;
    }

}
