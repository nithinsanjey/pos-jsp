<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date,java.text.SimpleDateFormat,com.nithin.pos.POJO.UserAccount"%>

<%
	String username = (String) session.getAttribute("loggedInUser");
	if(session.getAttribute("loggedInUser")==null || session.getAttribute("loggedInUser")==""){
%>
	<jsp:include page="login.jsp"/>
<%		
	}
	else{
		
			if (false){// need to implement product java code
%>
	<h3>New user <% out.println(username); %> created</h3>
	<jsp:include page="welcome.jsp"></jsp:include>
<%				
			}
			else {
%>
	<h3>New product creation failed due to error, please try again</h3>
	<jsp:include page="addProduct.jsp"></jsp:include>
<%
			}
		}
	
%>