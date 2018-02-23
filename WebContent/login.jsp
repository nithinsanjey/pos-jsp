<%@page import="com.nithin.pos.DAO.UserLoginDAO"%>  
<jsp:useBean id="obj" class="com.nithin.pos.POJO.UserLogin"/>  
  
<jsp:setProperty property="*" name="obj"/>  
<%@ page errorPage="errors.jsp" %>  
<%
	if(session.getAttribute("loggedInUser") != "" && session.getAttribute("loggedInUser") != null){
		out.println("<h5>Welcome back " +  session.getAttribute("loggedInUser") + "</h5>");
%>
	<jsp:include page="welcome.jsp"></jsp:include>
<%
	} else {
		String username = obj.getUsername();
		boolean status = UserLoginDAO.validateLogin(obj.getUsername(), obj.getPassword());
		if(status) {
			session.setAttribute("loggedInUser", username);
			out.println("<h3>Welcome " +  session.getAttribute("loggedInUser") + "</h3>");
%>
	<jsp:include page="welcome.jsp"></jsp:include>
<%
	}
		else {
			if(username == null)
				out.println("You have to login to access this page..");
			else
			out.println("Sorry, invalid login credentials. Please try again");
	%>
		<jsp:include page="index.jsp"></jsp:include>
	<% 
		}
	}
%>