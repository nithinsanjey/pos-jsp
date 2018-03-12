package com.nithin.pos.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nithin.pos.POJO.ProductDetail;
import com.nithin.pos.Util.ConnectionProvider;

public class ProductDetailDAO {
	public static boolean createProduct(ProductDetail productDetail) {
		Connection con = ConnectionProvider.getCon();
		String sql = "INSERT INTO PRODUCT_DETAIL(PRODUCT_NAME,QUANTITY,PRICE) VALUES(?,?,?)";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, productDetail.getProductName());
			ps.setInt(2, productDetail.getQuantity());
			ps.setFloat(3, productDetail.getPrice());
			int status = ps.executeUpdate();
			if (status == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			ConnectionProvider.closeQuietly(ps);
			ConnectionProvider.closeQuietly(con);
		}
	}
	
	public static List<ProductDetail> getAllProductDetails(){
		Connection con = ConnectionProvider.getCon();
		String sql = "SELECT * FROM PRODUCT_DETAIL";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 ps = con.prepareStatement(sql);
			 rs = ps.executeQuery();
			 List<ProductDetail> productsDetail = new ArrayList<ProductDetail>();
			 while(rs.next()) {
				 ProductDetail productDetail = new ProductDetail();
				 productDetail.setProductId(rs.getInt(1));
				 productDetail.setProductName(rs.getString(2));
				 productDetail.setQuantity(rs.getInt(3));
				 productDetail.setPrice(rs.getFloat(4));
				 productsDetail.add(productDetail);
			 }
			 return productsDetail;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			ConnectionProvider.closeQuietly(rs);
			ConnectionProvider.closeQuietly(ps);
			ConnectionProvider.closeQuietly(con);
		}
		
	}
}
