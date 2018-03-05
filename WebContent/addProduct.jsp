<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("loggedInUser") != "" && session.getAttribute("loggedInUser") != null){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add product</title>
</head>
<body>
<form action="addProductController.jsp" method="post">
	Product name : <input type="text" required name="productName">
	Quantity : <input type="number" min="0" name="quantity">
	Rate : <input type="number" required name="price" min="0" value="0" step="any">
	
	<p><input type="submit" value="Add product"></p>
</form>
<a href="welcome.jsp"><button>back</button></a><br>
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