package com.example.demo.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "userdb")
public class UserEntity extends BaseEntity{
	
	@Column
	private String fullname;
	
	@Column
	private String userName;
	
	@Column
	private String password;
	
	@Column
	private String address;
	
	@Column
	private String phoneNumber;
	
	@Column
	private String email;
	
	public UserEntity(String fullname, String userName, String password, String address, String phoneNumber, String email) {
		this.fullname = fullname;
		this.userName = userName;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public UserEntity() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
