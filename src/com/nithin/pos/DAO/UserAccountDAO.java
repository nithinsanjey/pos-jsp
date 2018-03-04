package com.nithin.pos.DAO;

import java.sql.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nithin.pos.POJO.UserAccount;
import com.nithin.pos.Util.ConnectionProvider;

public class UserAccountDAO {
	private static String hashAlgorithm = "SHA-512";
	public static boolean validateLogin(String username, String password) {
		//get the corresponding password salt
		//use the method
		//boolean validationResult = validatePassword(passwordSalt1, passwordHash, password, "SHA-512");
		Connection con = ConnectionProvider.getCon();
		String ppdSql = "SELECT PASSWORD_SALT,PASSWORD FROM USER_ACCOUNT WHERE USERNAME=?";
		String ppd, passwordHash;
		try {
			PreparedStatement ps = con.prepareStatement(ppdSql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ppd = rs.getString(1);
				passwordHash = rs.getString(2);
			}
			else {
				return false;
			}
			if (validatePassword(ppd, passwordHash, password, hashAlgorithm)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean insertUser(UserAccount userAccount) {
		//validate user data here
		
		//insert into database here
		Connection con = ConnectionProvider.getCon();
		String sql = "INSERT INTO USER_ACCOUNT(USERNAME,PASSWORD,PASSWORD_SALT,PASSWORD_HASH_ALGORITHM,FIRST_NAME,LAST_NAME,GENDER,DOB,USER_ADDED_BY,ROLE,BRANCH,BRAND) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userAccount.getUsername());
			String passwordSalt = generatePasswordSalt();
			String passwordHashAlgo = hashAlgorithm;
			
			ps.setString(2, calculatePasswordHash(passwordSalt, userAccount.getPassword(), passwordHashAlgo));
			ps.setString(3, passwordSalt);
			ps.setString(4, passwordHashAlgo);
			ps.setString(5, userAccount.getFirstName());
			ps.setString(6, userAccount.getLastName());
			ps.setString(7, userAccount.getGender());
			ps.setDate(8, userAccount.getDob());
			ps.setString(9, userAccount.getUserAddedBy());
			ps.setString(10, userAccount.getRole());
			ps.setString(11, userAccount.getBranch());
			ps.setString(12, userAccount.getBrand());
			
			int status = ps.executeUpdate();
			if (status == 1) 
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private static String generatePasswordSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        //System.out.println((bytes[0]));

        StringBuffer passwordSalt = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            passwordSalt.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        System.out.println("Hex format : " + passwordSalt.toString());
        //returns a 64 length hexString
        return passwordSalt.toString();
    }

    private static String calculatePasswordHash(String salt, String password, String hashAlgorithm){
        //salt is a hex String
        byte[] saltBytes = hexStringToByteArray(salt);
        byte[] passwordBytes = password.getBytes();
        byte[] combined = new byte[saltBytes.length + passwordBytes.length];

        for (int i = 0; i < combined.length; ++i)
        {
            combined[i] = i < saltBytes.length ? saltBytes[i] : passwordBytes[i - saltBytes.length];
        }

        return hashUsingAlgorithm(combined, hashAlgorithm);
    }

    //method to return byte array on hex string
    private static byte[] hexStringToByteArray(String hexString){
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i+1), 16));
        }
        return data;
    }

    private static String hashUsingAlgorithm(byte[] specimen, String hashAlgorithm){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(specimen);

        byte byteData[] = md.digest();
        //byteData

        //convert the byte to hex format
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    private static boolean validatePassword(String passwordSalt, String passwordHash, String password, String hashAlgorithm){
        if (calculatePasswordHash(passwordSalt, password, hashAlgorithm).equals(passwordHash))
            return true;
        else
            return false;
    }
    
    public static boolean changePassword(String username, String password) {
    	Connection con = ConnectionProvider.getCon();
    	String sql = "UPDATE USER_ACCOUNT SET PASSWORD=?,PASSWORD_SALT=? WHERE USERNAME=?";
    	String passwordSalt = generatePasswordSalt();
    	String passwordHash = calculatePasswordHash(passwordSalt, password, hashAlgorithm);
    	try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, passwordHash);
			ps.setString(2, passwordSalt);
			ps.setString(3, username);
			int status = ps.executeUpdate();
			if (status == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
}
