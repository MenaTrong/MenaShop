package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

@Controller
public class SignUpController {
	
	@Autowired
	private final UserService userService;
	
	// Contructor liên kết với class
	public SignUpController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/sign")
	public String signUp(@RequestParam("username") String username,
												@RequestParam("pass") String password,
												@RequestParam("fullname") String fullname,
												@RequestParam("address") String address,
												@RequestParam("phonenumber") String phonenumber,
												@RequestParam("email") String email) {

		try {
			
			boolean signupBoolean = userService.createAccount(fullname, password, username, address, phonenumber, email);
			
			if (signupBoolean) {
				return "redirect:/signup?success";
			} else {
				return "redirect:/signup?failure";
			}
			
	    } catch (Exception e) {
	    	return "redirect:/signup?failure";
	    }
	}
	
}
