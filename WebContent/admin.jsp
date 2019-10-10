<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Home page</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center>
			<h2>UTA Mac Facility Maintenance System</h2>
		</center>
		
		<h4> Admin Home page</h4>
		<div align="right">
			<a href="UserController?action=home&role=Admin&admin=<c:out value='${username}'/><c:out value='${admin}'/>">Home</a>&nbsp;
			<a href="UserController?action=search&admin=<c:out value='${username}'/><c:out value='${admin}'/>">Search user</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<div class="text-l"></div><br>
		<h1>WELCOME, <c:out value='${username}'/><c:out value='${admin}'/></h1>
	</div>
</body>
</html>