<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.nithin.pos.POJO.ProductDetail,com.nithin.pos.DAO.ProductDetailDAO"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if(session.getAttribute("loggedInUser") != "" && session.getAttribute("loggedInUser") != null){
		List<ProductDetail> allProducts = new ArrayList<ProductDetail>();
		allProducts = ProductDetailDAO.getAllProductDetails();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View product</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style type="text/css">
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 15px;
}
</style>
</head>
<body class="container-fluid">
<h3>All available product details</h3>
<%
	if(allProducts.size() == 0){
		out.println("No products available");
	}
	else{
%>
	<table>
		<tr>
			<th>Product ID</th>
			<th>Name</th>
			<th>Available quantity</th>
			<th>Price per quantity</th>
		</tr>
<%
		for(int i=0; i< allProducts.size(); i++)
		{
			out.println("<tr>");
			out.println("<td>" + allProducts.get(i).getProductId() + "</td>");
			out.println("<td>" + allProducts.get(i).getProductName().toUpperCase() + "</td>");
			out.println("<td>" + allProducts.get(i).getQuantity() + "</td>");
			out.println("<td>" + allProducts.get(i).getPrice() + "</td>");
			out.println("</tr>");
		}
%>
	</table>
<%
	}
%>
<br>
<a href="welcome.jsp"><button>Back</button></a><br>
</body>
</html>
<%
	}
	else {
%>
	<jsp:forward page="login.jsp"></jsp:forward>
<%
	}
%>