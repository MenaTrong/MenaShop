package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{

	List<CartItemEntity> findByCart(CartEntity cart);

}
