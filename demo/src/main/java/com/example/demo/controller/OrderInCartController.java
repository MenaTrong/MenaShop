package com.example.demo.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
public class OrderInCartController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private EmailService emailService;

	public OrderInCartController(OrderService orderService, ProductRepository productRepository,
			EmailService emailService) {
		this.orderService = orderService;
		this.productRepository = productRepository;
		this.emailService = emailService;
	}

	@GetMapping("/api")
	public ResponseEntity<String> createOrder(@RequestParam("kh_ten") String name,
								@RequestParam("kh_diachi") String address,
								@RequestParam("kh_dienthoai") String phonenumber,
								@RequestParam("kh_email") String email,
								@RequestParam("httt_ma") String httt,
								@RequestParam("sizeBuy") List<String> size,
								@RequestParam("quantityBuy") List<String> quantity,
								@RequestParam("totalPriceBuy") List<String> totalPrice,
								@RequestParam("productIdBuy") List<String> productId,
	                              Model model) {
		
		try {
			boolean saveSuccess = false;
			
			Long sumTotalPrice = (long) 0;
			
			for (int i = 0; i < productId.size(); i++) {
				sumTotalPrice += Long.parseLong(totalPrice.get(i));
			}

			LocalDateTime now = LocalDateTime.now();
			DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
			DateTimeFormatter invoiceId = DateTimeFormatter.ofPattern("HHmmss");
			String formattedDate = now.format(formatterDate);
			String formattedTime = now.format(formatterTime);
			String formatinvoiceId = now.format(invoiceId);

			saveSuccess = orderService.processOrder(name, address, phonenumber, email, productId, quantity, size,
					totalPrice, formattedDate, formattedTime, formatinvoiceId, httt, "Xác nhận đơn");

			if (saveSuccess) {
				String emailContent = "MenaShop xin chào " + name + ",\n\n";
				emailContent += "Cảm ơn bạn đã mua sản phẩm của Shop vào lúc " + formattedDate + ".\n";
				emailContent += "Đơn hàng của bạn đã được xác nhận và sẽ được xử lý sớm nhất.\n\n";
				emailContent += "Chi tiết đơn hàng:\n";

				for (int i = 0; i < productId.size(); i++) {
					emailContent += "- Sản phẩm: "
							+ productRepository.findById(Long.parseLong(productId.get(i))).get().getName() + "\n";
					emailContent += "- Số lượng: " + Integer.parseInt(quantity.get(i)) + "\n";
					emailContent += "- Kích thước: " + size.get(i) + "\n";
					emailContent += "- Giá: " + Long.parseLong(totalPrice.get(i)) + "VND\n\n";
				}
				
				emailContent += "- Tổng giá: " + sumTotalPrice + "VND\n\n";
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
