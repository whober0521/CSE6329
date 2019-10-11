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
		
		<h4> Facility Manager Home page</h4>
		<div align="right">
			<a href="UserController?action=home&role=Facility Manager&username=<c:out value='${username}'/>">Home</a>&nbsp;
			<a href="#">Update profile</a>&nbsp;
			<a href="MARController?action=available&username=<c:out value='${username}'/>">Search facility</a>&nbsp;
			<a href="FacilityController?action=add">Add new facility</a>&nbsp;
			<a href="MARController?action=assigned&username=<c:out value='${username}'/>">View Assignment Problems</a>&nbsp;
			<a href="MARController?action=unassigned&username=<c:out value='${username}'/>">View Unassigned Problems</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<div class="text-l"></div><br>
		
		<h1>WELCOME, <c:out value='${username}'/></h1>
	</div>
</body>
</html>
