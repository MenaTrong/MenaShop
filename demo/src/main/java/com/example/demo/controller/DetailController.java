package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ProductService;

@Controller
public class DetailController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CartRepository cartProductRepository;
	@Autowired
	private UserRepository userRepository;

	// Contructor liên kết với class
	public DetailController(ProductService productService, CartRepository cartProductRepository, UserRepository userRepository) {
		this.productService = productService;
		this.cartProductRepository = cartProductRepository;
		this.userRepository = userRepository;
	}

	@GetMapping("/detail")
	public String showDetail(Model model, @RequestParam(name = "id") Long productId) {

		Optional<ProductEntity> productOptional = productService.getOneProductById(productId);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		CartEntity cartItems = cartProductRepository.findByUserName(username);

		if (cartItems != null) {
			int soLuongSanPham = cartItems.getCartItems().size();
			model.addAttribute("soLuongSanPham", soLuongSanPham);
		} else {
			model.addAttribute("soLuongSanPham", 0);
		}
		
		if (authentication != null && authentication.isAuthenticated()) {
			Optional<UserEntity> userOptional = userRepository.findByUserName(username);
			if (userOptional.isPresent()) {
				UserEntity user = userOptional.get();
				model.addAttribute("user", user);
			} else {
				model.addAttribute("user", null);
			}
		} else {
			model.addAttribute("user", null);
		}

		ProductEntity product = productOptional.get();
		
		model.addAttribute("product", product);
		model.addAttribute("username", username);

		return "detail";
	}

}
