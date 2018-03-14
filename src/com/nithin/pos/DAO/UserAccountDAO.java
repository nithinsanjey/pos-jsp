package com.nithin.pos.DAO;

import java.sql.Connection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nithin.pos.POJO.UserAccount;
import com.nithin.pos.Util.ConnectionProvider;
import static com.nithin.pos.Config.Provider.HASH_ALGORITHM;

public class UserAccountDAO {
	final static Logger log = Logger.getLogger(UserAccountDAO.class);
	
	public static boolean validateLogin(String username, String password) {
		
		Connection con = ConnectionProvider.getCon();
		String ppdSql = "SELECT PASSWORD_SALT,PASSWORD FROM USER_ACCOUNT WHERE USERNAME=?";
		String ppd, passwordHash;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(ppdSql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				ppd = rs.getString(1);
				passwordHash = rs.getString(2);
			}
			else {
				return false;
			}
			if (validatePassword(ppd, passwordHash, password, HASH_ALGORITHM)) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.error("Login failed ", e);
			return false;
		} finally {
			ConnectionProvider.closeQuietly(rs);
			ConnectionProvider.closeQuietly(ps);
			ConnectionProvider.closeQuietly(con);
		}
	}
	
	public static boolean insertUser(UserAccount userAccount) {
		
		Connection con = ConnectionProvider.getCon();
		String sql = "INSERT INTO USER_ACCOUNT(USERNAME,PASSWORD,PASSWORD_SALT,PASSWORD_HASH_ALGORITHM,FIRST_NAME,LAST_NAME,GENDER,DOB,USER_ADDED_BY,ROLE,BRANCH,BRAND) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, userAccount.getUsername());
			String passwordSalt = generatePasswordSalt();
			
			ps.setString(2, calculatePasswordHash(passwordSalt, userAccount.getPassword(), HASH_ALGORITHM));
			ps.setString(3, passwordSalt);
			ps.setString(4, HASH_ALGORITHM);
			ps.setString(5, userAccount.getFirstName());
			ps.setString(6, userAccount.getLastName());
			ps.setString(7, userAccount.getGender());
			ps.setDate(8, userAccount.getDob());
			ps.setString(9, userAccount.getUserAddedBy());
			ps.setString(10, userAccount.getRole());
			ps.setString(11, userAccount.getBranch());
			ps.setString(12, userAccount.getBrand());
			
			int status = ps.executeUpdate();
			
			if (status == 1) {
				log.info("SUCCESS :: Adding user : " + userAccount.getUsername());
				return true;
			}
			else {
				log.info("FAILED :: Adding user : " + userAccount.getUsername() + " with status : " + status);
				return false;
			}
			
		} catch (SQLException e) {
			log.error("FAILED :: Adding user : " + userAccount.getUsername() , e);
			return false;
		} finally {
			ConnectionProvider.closeQuietly(ps);
			ConnectionProvider.closeQuietly(con);
		}
	}
	
	public static boolean changePassword(String username, String password) {
    	Connection con = ConnectionProvider.getCon();
    	String sql = "UPDATE USER_ACCOUNT SET PASSWORD=?,PASSWORD_SALT=? WHERE USERNAME=?";
    	String passwordSalt = generatePasswordSalt();
    	String passwordHash = calculatePasswordHash(passwordSalt, password, HASH_ALGORITHM);
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(sql);
			ps.setString(1, passwordHash);
			ps.setString(2, passwordSalt);
			ps.setString(3, username);
			int status = ps.executeUpdate();
			if (status == 1) {
				log.info("SUCCESS :: Change password for user : " + username);
				return true;
			}
			else {
				log.info("FAILED :: Change password for user : " + username + " with status : " + status);
				return false;
			}
		} catch (SQLException e) {
			log.error("FAILED :: Change password for user : " + username, e);
		} finally {
			ConnectionProvider.closeQuietly(ps);
			ConnectionProvider.closeQuietly(con);
		}
    	return false;
    }
	
	private static String generatePasswordSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);

        StringBuffer passwordSalt = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            passwordSalt.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
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
}
