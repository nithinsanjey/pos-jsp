<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("loggedInUser") != "" && session.getAttribute("loggedInUser") != null){
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change password</title>
</head>
<body>
<h3>Change password screen :</h3>
<form action="changePasswordController.jsp" method="post">
	Current password : <input type="password" name="currentPassword">
	<p>Set the password here</p>
	Password : <input type="password" name="newPassword">
	Re-enter password : <input type="password" name="repassword">
	<p><input type="submit" value="Change password"></p>
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