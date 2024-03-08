package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Long>{

	CartEntity findByUserName(String userName);

}
