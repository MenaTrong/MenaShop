package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	
	@Query("SELECT o FROM OrderEntity o WHERE o.fullName = :fullName AND o.status = :status")
	List<OrderEntity> findByFullNameAndActiveStatus(@Param("fullName") String fullName, @Param("status") String status);
	
	@Query("SELECT o FROM OrderEntity o WHERE o.fullName = :fullName AND o.status = :status AND o.date = :date")
    List<OrderEntity> findByFullNameAndActiveStatusAndDate(@Param("fullName") String fullName, @Param("status") String status, @Param("date") String date);

	@Query("SELECT o FROM OrderEntity o WHERE o.fullName = :fullName AND o.date = :date")
    List<OrderEntity> findByFullNameAndDate(@Param("fullName") String fullName, @Param("date") String date);
	
	List<OrderEntity> findByFullName(String fullName);
	
	

}
