package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{

	Page<ProductEntity> findAll(Pageable pageable);
	
	Page<ProductEntity> findByNameContaining(String keyword, Pageable pageable);
	
	Optional<ProductEntity> findById(Long productId);
	
	@Query("SELECT o FROM ProductEntity o WHERE o.type = :type")
    Page<ProductEntity> findByType(@Param("type") String type, Pageable pageable);
	
	Page<ProductEntity> findAllByOrderByPriceAsc(Pageable pageable);
	
	Page<ProductEntity> findAllByOrderByPriceDesc(Pageable pageable);
}
