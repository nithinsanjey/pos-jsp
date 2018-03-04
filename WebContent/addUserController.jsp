<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.Date,java.text.SimpleDateFormat,com.nithin.pos.POJO.UserAccount"%>

<%
	if(session.getAttribute("loggedInUser")==null || session.getAttribute("loggedInUser")==""){
%>
	<jsp:include page="login.jsp"/>
<%		
	}
	else{
		
	String password = request.getParameter("password");
	String repassword = request.getParameter("repassword");
		if(!password.equals(repassword))
		{
%>
	<h3>The passwords did not match please enter the details again</h3>
	<jsp:include page="addUser.jsp"/>
<%
		}
		else{
			String username = request.getParameter("username");
			String firstName = request.getParameter("firstname");
			String lastName = request.getParameter("lastname");
			String gender = request.getParameter("gender");
			
			//Date drama
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = request.getParameter("dob");
			System.out.println(dateInString + " This is the date in string");
			java.util.Date date = formatter.parse(dateInString);
			System.out.println(date + "this is the date" + " and this is the time " + date.getTime());
			Date dob = new java.sql.Date(date.getTime());
			
			
			UserAccount userAccount = new UserAccount();
			userAccount.setUsername(username);
			userAccount.setFirstName(firstName);
			userAccount.setLastName(lastName);
			userAccount.setGender(gender);
			userAccount.setDob(dob);
			userAccount.setPassword(password);
			//Default extension ability fields
			userAccount.setUserAddedBy("admin".toUpperCase());
			userAccount.setRole("MANAGER");
			userAccount.setBranch("HOME");
			userAccount.setBrand("HOME");
			
			if (userAccount.insertUser(userAccount)){
%>
	<h3>New user <% out.println(username); %> created</h3>
	<jsp:include page="welcome.jsp"></jsp:include>
<%				
			}
			else {
%>
	<h3>New user creation failed due to error, please try again</h3>
	<jsp:include page="addUser.jsp"></jsp:include>
<%
			}
		}
	}
%>