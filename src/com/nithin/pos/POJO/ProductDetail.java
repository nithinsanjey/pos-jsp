package com.nithin.pos.POJO;

import com.nithin.pos.DAO.ProductDetailDAO;

public class ProductDetail {
	int productId;
	String productName;
	int quantity;
	float price;
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public boolean createProduct() {
		return ProductDetailDAO.createProduct(this);
	}
}
