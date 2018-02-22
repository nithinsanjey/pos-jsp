package com.nithin.pos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nithin.pos.Util.ConnectionProvider;


public class UserLoginDAO {
	
	public static boolean validateLogin(String username, String password) {
		Connection con = ConnectionProvider.getCon();
		
		String sql = "SELECT * FROM USER_LOGIN WHERE USERNAME=? AND PASSWORD=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			else 
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return true;
	}
}