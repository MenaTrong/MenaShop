package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.CartProductService;
import com.example.demo.service.UserService;

@Controller
public class AddProductToCartController {

	private CartProductService cartProductService;
	private UserService userService;

	public AddProductToCartController(CartProductService cartProductService, UserService userService) {
		this.cartProductService = cartProductService;
		this.userService = userService;
	}

	@GetMapping("/addcart")
	public ResponseEntity<String> addToCart(@RequestParam("id") Long productId,
											@RequestParam("username") String userName,
											@RequestParam("quantity") int quantity,
											@RequestParam("size") String size) {
		try {
			cartProductService.addToCartNew(userName, productId, quantity, size);
			int quantityString = userService.getNumberCartItems();
			String newQuantity = Integer.toOctalString(quantityString);
			return ResponseEntity.ok(newQuantity);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("Error adding to cart");
		}
	}
	
}
