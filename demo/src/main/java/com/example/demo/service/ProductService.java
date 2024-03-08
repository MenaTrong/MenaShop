package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	

	public ProductService(ProductRepository productRepository, CartItemRepository cartItemRepository) {
		this.productRepository = productRepository;
		this.cartItemRepository = cartItemRepository;
	}
	
	public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }
	
	public Page<ProductEntity> getAllProductsToShow(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
	
	public Optional<ProductEntity> getOneProductById(Long id) {
		return productRepository.findById(id);
	}
	
	public void addToCart(ProductEntity productEntity) {
		productRepository.save(productEntity);
	}
	
	public void removeFromCart(Long cartItemId) {
		System.out.println(cartItemId);
        cartItemRepository.deleteById(cartItemId);
    }
	
	public Page<ProductEntity> searchProducts(String keyword, Pageable pageable) {
        return productRepository.findByNameContaining(keyword, pageable);
    }
	
}
