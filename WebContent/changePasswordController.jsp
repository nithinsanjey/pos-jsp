<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.nithin.pos.POJO.UserAccount,com.nithin.pos.DAO.UserAccountDAO"%>
<%
	if(session.getAttribute("loggedInUser")==null || session.getAttribute("loggedInUser")==""){
%>
	<jsp:include page="login.jsp"/>
<%		
	}
	else{
		String username = (String) session.getAttribute("loggedInUser");
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("newPassword");
		String repassword = request.getParameter("repassword");
			if(!password.equals(repassword))
			{
%>
	<h3>The passwords did not match please enter the details again</h3>
	<jsp:include page="changePassword.jsp"/>
<%
		}
		else{
			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(username);
			if (UserAccountDAO.validateLogin(username, currentPassword)) {
				if (!userAccount.changePassword(password)){
%>
	<h3>Password change failed due to some error</h3>
	<jsp:include page="changePassword.jsp"></jsp:include>
<%
				}
				else{
%>
	<h3>Password changed successfully!</h3>
	<jsp:include page="welcome.jsp"></jsp:include>
<%	
				}
			}
			else {
%>
	<h3>Your password is wrong</h3>
	<jsp:include page="changePassword.jsp"></jsp:include>
<%
			}
		}
	}
%>