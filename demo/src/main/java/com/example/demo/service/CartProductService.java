package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CartProductService {

	@Autowired
	private final CartRepository cartProductRepository;
	
	@Autowired
	private final CartItemRepository cartItemRepository;
	
	@Autowired
    private ProductRepository productRepository;


    public CartProductService(CartRepository cartProductRepository, CartItemRepository cartItemRepository,
			ProductRepository productRepository) {
		this.cartProductRepository = cartProductRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
	}

	public List<CartEntity> getAllProductsInCart() {
        return cartProductRepository.findAll();
    }
	
	public void addToCart(String productName, String userName) {
        CartEntity cartEntity = new CartEntity(productName, userName, (long) 100000);
        cartProductRepository.save(cartEntity);
    }
	
	public void addToCartNew(String userName, Long productId, int quantity, String size) {
		// Kiểm tra xem giỏ hàng của người dùng đã tồn tại chưa
		CartEntity cart = cartProductRepository.findByUserName(userName);
		
		// Nếu giỏ hàng chưa tồn tại, tạo mới
        if (cart == null) {
            cart = new CartEntity();
            cart.setUserName(userName);
            cart.setCartItems(new ArrayList<>());
        }
        
        // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
        Optional<CartItemEntity> existingCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .filter(item -> item.getSize().equals(size))
                .findFirst();
        
        // Nếu sản phẩm đã có trong giỏ hàng, tăng số lượng
        if (existingCartItem.isPresent()) {
            existingCartItem.get().setQuantity(existingCartItem.get().getQuantity() + quantity);
            cartProductRepository.save(cart);
        } else {
            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            ProductEntity product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItem.setSize(size);
            cart.getCartItems().add(cartItem);
            
            // Tính toán giá giỏ hàng
            cart.setPrice(cart.getCartItems().stream()
                    .mapToLong(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum());
            cartProductRepository.save(cart);
            
        }
        
	}

	public CartEntity getCartByUserName(String username) {
		return cartProductRepository.findByUserName(username);
	}
	
	public List<CartItemEntity> getCartItems(CartEntity cart) {
        return cartItemRepository.findByCart(cart);
    }
	
	public Long totalPayCart(String userName) {
		
		CartEntity cart = cartProductRepository.findByUserName(userName);
		cart.setPrice(cart.getCartItems().stream()
                .mapToLong(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum());
		
        cartProductRepository.save(cart);
        return cart.getPrice();
	}
	
}
