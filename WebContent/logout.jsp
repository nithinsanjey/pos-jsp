<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="org.apache.log4j.Logger"%>
<%! static Logger log = Logger.getLogger("logout.jsp"); %>
<%
	String username = (String) session.getAttribute("loggedInUser");
	session.setAttribute("loggedInUser", "");
	session.invalidate();
	log.info("Logged out user :: " + username);
%>
<%= "Logout successfully" %>
<jsp:include page="index.jsp"></jsp:include>