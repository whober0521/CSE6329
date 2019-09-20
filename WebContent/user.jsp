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
	
	<h4> User Home page</h4>
	
	<div align="right">
		<a href="user.jsp">Home</a>&nbsp;
		<a href="#">View profile</a>&nbsp;
		<a href="#">Update Profile</a>&nbsp;
		<a href="MARController?action=report">Create Problem Report</a>&nbsp;
		<a href="#">Search Problem Reports</a>&nbsp;
		<a href="UserController?action=logout">Logout</a>
		<hr>
	</div>
	
	<div class="text-l"></div><br>
	
	<h1>WELCOME, <c:out value='${user.username}'/></h1>
</body>
</html>