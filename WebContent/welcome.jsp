<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="container-fluid">
	<jsp:include page="header.jsp"></jsp:include>
	<h2>Welcome to POS application</h2>
	<a href="addUser.jsp"><button>Add user</button></a>
	<a href="addProduct.jsp"><button>Add product</button></a>
	<a href="viewProducts.jsp"><button>View all products</button></a>
	<a href="changePassword.jsp"><button>Change my password</button></a>
</body>
</html>