package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.OrderItemsEntity;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public List<OrderEntity> getAllOrderList() {
		return orderRepository.findAll();
	}
	
	public boolean processOrder(String kh_tendangnhap, String address, String phonenumber, String email, List<String> productId, List<String> quantity, List<String> size,
			List<String> price, String formattedDate, String formattedTime, String formatinvoiceId, String httt,
			String string) {
		
		try {
			// Xử lý dữ liệu và lưu vào cơ sở dữ liệu
			OrderEntity order = new OrderEntity();
			order.setFullName(kh_tendangnhap);
			order.setAddress(address);
			order.setPhoneNumber(phonenumber);
			order.setEmail(email);
			order.setDate(formattedDate);
			order.setTime(formattedTime);
			order.setInvoiceId(formatinvoiceId);
			order.setPayment(httt);
			order.setStatus(string);

			for (int i = 0; i < productId.size(); i++) {
				OrderItemsEntity orderItem = new OrderItemsEntity();
				orderItem.setProductId(Long.parseLong(productId.get(i)));
				orderItem.setQuantity(Integer.parseInt(quantity.get(i)));
				orderItem.setSize(size.get(i));
				orderItem.setPrice(Long.parseLong(price.get(i)));

				order.addOrderItem(orderItem);
				// Lưu orderItem vào order
			}
			// Lưu order vào cơ sở dữ liệu
			orderRepository.save(order);
			return true;
		} catch (Exception e) {
			return true;
		}
		
	}

	public boolean saveOrder(String name, String address, String phonenumber, String email, Long productId, String size,
			int quantity, Long totalPrice, String httt, String formattedDate, String formattedTime, String formatinvoiceId, String string) {

		try {
			// Xử lý dữ liệu và lưu vào cơ sở dữ liệu
			OrderEntity order = new OrderEntity();
			order.setFullName(name);
			order.setAddress(address);
			order.setPhoneNumber(phonenumber);
			order.setEmail(email);
			order.setDate(formattedDate);
			order.setTime(formattedTime);
			order.setInvoiceId(formatinvoiceId);
			order.setPayment(httt);
			order.setStatus(string);
			
			OrderItemsEntity orderItem = new OrderItemsEntity();
			orderItem.setProductId(productId);
			orderItem.setQuantity(quantity);
			orderItem.setSize(size);
			orderItem.setPrice(totalPrice);
			order.addOrderItem(orderItem);
			
			orderRepository.save(order);

			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
