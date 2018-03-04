package com.nithin.pos.POJO;

import java.sql.Date;

import com.nithin.pos.DAO.UserAccountDAO;

public class UserAccount {
	int userId;
	String username;
	String password;
	String passwordSalt;
	String passwordHashAlgorithm;
	String firstName;
	String lastName;
	String gender;
	Date dob;
	String userAddedBy;
	String role;
	String branch;
	String brand;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}
	public String getPasswordHashAlgorithm() {
		return passwordHashAlgorithm;
	}
	public void setPasswordHashAlgorithm(String passwordHashAlgorithm) {
		this.passwordHashAlgorithm = passwordHashAlgorithm;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getUserAddedBy() {
		return userAddedBy;
	}
	public void setUserAddedBy(String userAddedBy) {
		this.userAddedBy = userAddedBy;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean insertUser(UserAccount userAccount) {
		return UserAccountDAO.insertUser(userAccount);
	}
	
	public boolean changePassword(String password) {
		return UserAccountDAO.changePassword(this.username, password);
	}
}
