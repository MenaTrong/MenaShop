package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartProductService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class CartController {

	@Autowired
	private CartRepository cartProductRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CartProductService cartProductService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;

	// Contructor liên kết với class
	public CartController(CartRepository cartProductRepository, UserRepository userRepository,
			CartProductService cartProductService, ProductService productService, UserService userService) {
		this.cartProductRepository = cartProductRepository;
		this.userRepository = userRepository;
		this.cartProductService = cartProductService;
		this.productService = productService;
		this.userService = userService;
	}

	@GetMapping("/cart")
	public String showCartForm(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		CartEntity cartItems = cartProductRepository.findByUserName(username);
		CartEntity cart = cartProductService.getCartByUserName(username);

		if (cartItems != null) {
			int soLuongSanPham = cartItems.getCartItems().size();
			model.addAttribute("soLuongSanPham", soLuongSanPham);
		} else {
			model.addAttribute("soLuongSanPham", 0);
		}

		Optional<UserEntity> userOptional = userRepository.findByUserName(username);
		UserEntity user = userOptional.get();

		model.addAttribute("user", user);
		model.addAttribute("cart", cart);
		model.addAttribute("cartItems", cartProductService.getCartItems(cart));

		return "cart";
	}

	@GetMapping("/removeFromCart")
	public ResponseEntity<CartResponse> removeFromCart(@RequestParam("productId") Long productId) {

		productService.removeFromCart(productId);

		int quantityString = userService.getNumberCartItems();
		String newQuantity = Integer.toOctalString(quantityString);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		CartResponse cartResponse = new CartResponse(cartProductService.totalPayCart(username), newQuantity);

		return ResponseEntity.ok(cartResponse);
	}

	public class CartResponse {
		private String newQuantity;
		private Long totalPrice;

		public String getNewQuantity() {
			return newQuantity;
		}

		public void setNewQuantity(String newQuantity) {
			this.newQuantity = newQuantity;
		}

		public Long getTotalPrice() {
			return totalPrice;
		}

		public void setTotalPrice(Long totalPrice) {
			this.totalPrice = totalPrice;
		}

		public CartResponse(Long totalPrice, String newQuantity) {
			this.totalPrice = totalPrice;
			this.newQuantity = newQuantity;
		}
	}

}
