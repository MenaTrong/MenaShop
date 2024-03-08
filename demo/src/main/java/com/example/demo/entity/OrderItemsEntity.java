package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderItems")
public class OrderItemsEntity extends BaseEntity{

	@ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;
	
	@Column
	private Long productId;
	
	@Column
	private int quantity;
	
	@Column
    private Long price;
	
	@Column
	private String size;

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public OrderItemsEntity(OrderEntity order, Long productId, int quantity, Long price, String size) {
		this.order = order;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.size = size;
	}

	public OrderItemsEntity() {
	}
	
	
}
