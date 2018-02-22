package com.nithin.pos.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import static com.nithin.pos.Config.Provider.*;

public class ConnectionProvider {  
private static Connection con=null;  
static{  
try{  
	Class.forName(DRIVER);  
	con=DriverManager.getConnection(CONNECTION_URL,USERNAME,PASSWORD);
	}catch(Exception e){}  
}  
  
public static Connection getCon(){  
    return con;  
}  
  
}  