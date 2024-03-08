package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.OrderEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class PackingController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public PackingController(UserService userService, OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
		this.userService = userService;
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
	}

	@GetMapping("/packing")
	public String showPackingForm(Model model, @RequestParam(name = "selectedDate", required = false) String selectedDate) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Optional<UserEntity> userOptional = userRepository.findByUserName(username);
        UserEntity user = userOptional.get();
        String fullName = user.getFullname();
		
		model.addAttribute("soLuongSanPham", userService.getNumberCartItems());
		
		if (selectedDate != null && !selectedDate.isEmpty()) {
			List<OrderEntity> listOrderEntities = orderRepository.findByFullNameAndActiveStatusAndDate(fullName, "Xác nhận đơn", selectedDate);
			model.addAttribute("listOrderItems", orderItemRepository.findByOrder(listOrderEntities));
		} else {
			List<OrderEntity> listOrderEntities = orderRepository.findByFullNameAndActiveStatus(fullName, "Xác nhận đơn");
			model.addAttribute("listOrderItems", orderItemRepository.findByOrder(listOrderEntities));
		}
		return "pack";
	}

}
