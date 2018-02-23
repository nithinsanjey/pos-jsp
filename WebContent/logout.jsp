<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	session.setAttribute("logggedInUser", "");
	session.invalidate();
%>
<%= "Logout successfully" %>
<jsp:include page="index.jsp"></jsp:include>