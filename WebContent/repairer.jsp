<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Home page</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center>
			<header>
				<h2>UTA Mac Facility Maintenance System</h2>
			</header>
		</center>
		
		<h4> Repairer Home page</h4>
		<div align="right">
			<a href="repairer.jsp">Home</a>&nbsp;
			<a href="#">Request Repair Reservation</a>&nbsp;
			<a href="MARController?action=search_r&username=<c:out value='${user.username}'/>">View My Reserved Repairs</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
	
		<div class="text-l"></div><br>
		<h1>WELCOME, <c:out value='${user.username}'/></h1>
	</div>
</body>
</html>