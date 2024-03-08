package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "oderdb")
public class OrderEntity extends BaseEntity {
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItemsEntity> orderItems = new ArrayList<>();
	
	@Column
	private String fullName;
	
	@Column
	private String address;
	
	@Column
	private String phoneNumber;

	@Column
	private String email;
	
	@Column
	private String payment;

	@Column
	private String status;

	@Column
	private String date;

	@Column
	private String time;

	@Column
	private String invoiceId;

	public OrderEntity(List<OrderItemsEntity> orderItems, String fullName, String address, String phoneNumber,
			String email, String payment, String status, String date, String time, String invoiceId) {
		this.orderItems = orderItems;
		this.fullName = fullName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.payment = payment;
		this.status = status;
		this.date = date;
		this.time = time;
		this.invoiceId = invoiceId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<OrderItemsEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemsEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderEntity() {
	}
	
	public void addOrderItem(OrderItemsEntity orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
