package com.nithin.pos.Config;

public interface Provider {
	String DRIVER="com.mysql.jdbc.Driver";  
	String CONNECTION_URL="jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";  
	String USERNAME="root";  
	String PASSWORD="mysql";  
	String HASH_ALGORITHM = "SHA-512";
}
