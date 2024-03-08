package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartitemdb")
public class CartItemEntity extends BaseEntity{

	@Column
	private String status;
	@Column
	private int quantity;
	@Column
	private String size;
	
	@ManyToOne
	@JoinColumn(name = "cartId")
    private CartEntity cart;

	@ManyToOne
    @JoinColumn(name = "productId")
    private ProductEntity product;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
    

}
