<%@page import="com.nithin.pos.DAO.UserLoginDAO"%>  
<jsp:useBean id="obj" class="com.nithin.pos.POJO.UserLogin"/>  
  
<jsp:setProperty property="*" name="obj"/>  
<%
	String username = obj.getUsername();
	boolean status = UserLoginDAO.validateLogin(obj.getUsername(), obj.getPassword());
	if(status) {
		out.println("Hello " + username + ", you are successfully logged in.");
		session.setAttribute("loggedInUser", username);
%>
	<jsp:include page="welcome.jsp"></jsp:include>
<%
	}
	else {
		out.println("Sorry, invalid login credentials. Please try again");
%>
	<jsp:include page="index.jsp"></jsp:include>
<% 
	}
%>