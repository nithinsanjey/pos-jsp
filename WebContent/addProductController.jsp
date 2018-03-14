<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date,java.text.SimpleDateFormat,com.nithin.pos.POJO.ProductDetail"%>

<%
	String username = (String) session.getAttribute("loggedInUser");
	if(session.getAttribute("loggedInUser")==null || session.getAttribute("loggedInUser")==""){
%>
	<jsp:include page="login.jsp"/>
<%		
	}
	else{
			ProductDetail productDetail = new ProductDetail();
			String productName = request.getParameter("productName");
			productDetail.setProductName(productName);
			productDetail.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			productDetail.setPrice(Float.parseFloat(request.getParameter("price")));
			if (productDetail.createProduct()) {
%>
	<h4>New product <% out.println(productName); %> added</h4>
	<jsp:include page="welcome.jsp"></jsp:include>
<%				
			}
			else {
%>
	<h3>New product addition failed due to error, please try again</h3>
	<jsp:include page="addProduct.jsp"></jsp:include>
<%
			}
		}
	
%>