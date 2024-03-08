package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;
	private CartRepository cartProductRepository;
	private PasswordEncoder passwordEncoder;

	// Contructor liên kết với class
	public UserService(UserRepository userRepository, CartRepository cartProductRepository) {
		this.userRepository = userRepository;
		this.cartProductRepository = cartProductRepository;
	}
	
	@Autowired
	@Lazy
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
				.password(user.getPassword()).roles("USER").build();
	}

	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	public UserEntity getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void saveUser(UserEntity user) {
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public int getNumberCartItems() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		String username = authentication.getName();

		CartEntity cartItems = cartProductRepository.findByUserName(username);

		if (cartItems != null) {
			int soLuongSanPham = cartItems.getCartItems().size();
			return soLuongSanPham;
		} else {
			return 0;
		}
	}

	public boolean createAccount(String fullName, String password, String userName, String address, String phoneNumber, String email) {
		
		UserEntity user = new UserEntity(fullName, userName, password, address, phoneNumber, email);

		Optional<UserEntity> userOptional = userRepository.findByUserName(userName);

		if (userOptional.isPresent()) {
			return false;
		} else {
			user.setPassword(passwordEncoder.encode(password));
			userRepository.save(user);
			return true;
		}
	}

}
