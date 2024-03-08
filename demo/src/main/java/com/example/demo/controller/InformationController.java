package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class InformationController {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	// Contructor liên kết với class
	public InformationController(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/information")
	public String showInformationForm(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Optional<UserEntity> userOptional = userRepository.findByUserName(username);
        UserEntity user = userOptional.get();
        
        model.addAttribute("user", user);
        model.addAttribute("soLuongSanPham", userService.getNumberCartItems());
		
        return "informationuser";
	}
	
}
