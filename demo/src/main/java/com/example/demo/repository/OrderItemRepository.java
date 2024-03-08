package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderItemsEntity;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Long>{

	@Query("SELECT oi FROM OrderItemsEntity oi WHERE oi.order IN :orders")
    List<OrderItemsEntity> findByOrder(@Param("orders") Iterable<OrderEntity> orders);
}
