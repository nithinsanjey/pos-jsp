<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("loggedInUser") != "" && session.getAttribute("loggedInUser") != null){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add user</title>
</head>
<body>
<form action="addUserController.jsp" method="post">
	User name : <input type="text" name="username">
	First name : <input type="text" name="firstname">
	Last name : <input type="text" name="lastname">
	Gender : <input type="text" name="gender">
	DOB : <input type="date" name="dob">
	
	<p>Set the password here</p>
	Password : <input type="password" name="password">
	Re-enter password : <input type="password" name="repassword">
	<p><input type="submit" value="Add user"></p>
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