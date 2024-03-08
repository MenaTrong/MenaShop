package com.example.demo.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.ProductRepository;
import com.example.demo.service.EmailService;
import com.example.demo.service.OrderService;

@Controller
public class ConfirmBuyNowController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/sendemail")
	public ResponseEntity<String> confirmOrder(@RequestParam("kh_ten") String name,
												@RequestParam("kh_diachi") String address,
												@RequestParam("kh_dienthoai") String phonenumber,
												@RequestParam("kh_email") String email,
												@RequestParam("httt_ma") String httt,
												@RequestParam("size") String size,
												@RequestParam("quantity") int quantity,
												@RequestParam("price") Long totalPrice,
												@RequestParam("productid") Long productId,
												Model model) {
		try {
			
			boolean saveSuccess = false;
			
			// Lấy ngày giờ
			LocalDateTime now = LocalDateTime.now();
	        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
	        DateTimeFormatter invoiceId = DateTimeFormatter.ofPattern("HHmmss");
	        String formattedDate = now.format(formatterDate);
	        String formattedTime = now.format(formatterTime);
	        String formatinvoiceId = now.format(invoiceId);
	        
	        saveSuccess = orderService.saveOrder(name, address, phonenumber, email, productId, size, quantity, totalPrice, httt, formattedDate, formattedTime,"HD" + formatinvoiceId + productId, "Xác nhận đơn");
	        
	        if (saveSuccess) {
	        	String emailContent = "MenaShop xin chào " + name + ",\n\n";
		        emailContent += "Cảm ơn bạn đã mua sản phẩm của Shop vào lúc " + formattedDate + ".\n";
		        emailContent += "Đơn hàng của bạn đã được xác nhận và sẽ được xử lý sớm nhất.\n\n";
		        emailContent += "Chi tiết đơn hàng:\n";
		        emailContent += "- Sản phẩm: "+ productRepository.findById(productId).get().getName() + "\n";
		        emailContent += "- Số lượng: " + quantity + "\n";
		        emailContent += "- Kích thước: " + size + "\n";
		        emailContent += "- Tổng giá: " + totalPrice + "VND\n\n";
		        emailContent += "- Hình thức thanh toán: " + httt + "\n\n";
		        emailContent += "Xin cảm ơn và chúc bạn một ngày tốt lành!\n\n";
		        emailContent += "Trân trọng!\n";
		        
		        emailService.sendSimpleMessage(email, "Xác nhận đơn", emailContent);
			}
	        
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/ordersuccessful");
			
			return new ResponseEntity<>(headers, HttpStatus.FOUND);
		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", "/error");
			
			return new ResponseEntity<>(headers, HttpStatus.FOUND);
		}
	}

}
