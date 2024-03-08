package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UserService;

@Controller
public class ViewController {
	
	@Autowired
	private UserService userService;
	
	// Contructor liên kết với class
	public ViewController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String showSignUpForm() {
		return "signup";
	}
	
	@GetMapping("/contact")
	public String showcontactForm(Model model) {
		model.addAttribute("soLuongSanPham", userService.getNumberCartItems());
		return "contact";
	}
	
	@GetMapping("/shop")
	public String showshopForm(Model model) {
		model.addAttribute("soLuongSanPham", userService.getNumberCartItems());
		return "shop";
	}
	
	@GetMapping("/ordersuccessful")
	public String showForm() {
		return "ordersuccessful";
	}
	
	@GetMapping("/error")
	public String showErrorForm() {
		return "error";
	}
	
}
