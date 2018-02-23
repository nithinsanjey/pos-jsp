<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
	<%@ page errorPage="errors.jsp" %>  
	<%
		String username = (String) session.getAttribute("loggedInUser");
		if ("".equals(username) || username == null)
		{
	%>
	<h2>Login to continue...</h2>
	<form action="login.jsp" method="post">
		Name : <input type="text" name="username"><br>
		Password : <input type="password" name="password"><br>
		<input type="submit" value="Login">
	</form>
	<%
		}
		else {
			out.println("<h5>Welcome back " + username + "</h5>");
	%>
	<jsp:include page="welcome.jsp"></jsp:include>
	<%
		}
	%>
</body>
</html>