package com.nithin.pos.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.nithin.pos.Config.Provider.*;

public class ConnectionProvider {    
	
	private ConnectionProvider() {
		
	}
public static Connection getCon(){
	try {
			Class.forName(DRIVER);  
			return DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
	}
    
}

public static void closeQuietly(ResultSet rs) {
	if (rs != null) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public static void closeQuietly(PreparedStatement ps) {
	if (ps != null) {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public static void closeQuietly(Connection obj) {
	if (obj != null) {
		try {
			obj.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}