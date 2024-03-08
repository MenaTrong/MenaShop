package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	// Contructor liên kết với class
	public HomeController(ProductService productService, UserService userService, CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.productService = productService;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@GetMapping("/home")
    public String showHomePage(Model model, @PageableDefault(page = 1, size = 8) Pageable pageable,
    		@RequestParam(name = "keyword", required = false) String keyword,
    		@RequestParam(name = "selectedCategory", required = false) String selectedCategory,
    		@RequestParam(name = "selectedSortByPrice", required = false) String selectedSortByPrice) {
		
		// Trừ đi 1 từ số trang được truyền vào để sử dụng trong xử lý phía server.
		Pageable adjustedPageable = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        Page<ProductEntity> products = productService.getAllProductsToShow(adjustedPageable);
        
        if (keyword != null && !keyword.isEmpty()) {
        	Pageable adjustedPageable1 = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        	products = productService.searchProducts(keyword, adjustedPageable1);
        } else {
            products = productService.getAllProductsToShow(adjustedPageable);
        }
        
        if (selectedCategory != null && !selectedCategory.isEmpty()) {
        	Pageable adjustedPageable1 = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        	products = productRepository.findByType(selectedCategory, adjustedPageable1);
        }
        
        if (selectedSortByPrice != null && !selectedSortByPrice.isEmpty()) {
        	Pageable adjustedPageable1 = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        	System.out.println(selectedSortByPrice);
        	if (selectedSortByPrice.equals("Giảm dần")) {
        		products = productRepository.findAllByOrderByPriceDesc(adjustedPageable1);
        	} else {
        		products = productRepository.findAllByOrderByPriceAsc(adjustedPageable1);
        	}
        }
        
        // Số trang cần hiển thị gần trang hiện tại
        int totalPages = products.getTotalPages();
        int currentPage = products.getNumber() + 1;
        
        // Xây dựng danh sách các trang cần hiển thị
        List<Integer> pageNumbers = new ArrayList<>();
        int startPage = Math.max(1, currentPage - 1);
        int endPage = Math.min(totalPages, startPage + 2);

        for (int i = startPage; i <= endPage; i++) {
            pageNumbers.add(i);
        }
        
        model.addAttribute("category", categoryRepository.findAll());
        model.addAttribute("products", products);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("soLuongSanPham", userService.getNumberCartItems());
        
        return "index";
    }
}
