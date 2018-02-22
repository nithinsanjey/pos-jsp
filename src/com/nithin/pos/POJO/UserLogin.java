package com.nithin.pos.POJO;

import com.nithin.pos.DAO.UserLoginDAO;

public class UserLogin {
	String username;
	String password;
	
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
	
	public boolean validateLogin() {
		return UserLoginDAO.validateLogin(this.getUsername(), this.getPassword());  // I think this is a better approach to validation, what say? someone?
	}
}