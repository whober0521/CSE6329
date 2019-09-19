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
	
	<h4> Admin page</h4>
	<div align="right">
		<a href="admin.jsp">Home</a>&nbsp;
		<a href="#">View profile</a>&nbsp;
		<a href="UserController?action=search">Search user</a>&nbsp;
		<a href="UserController?action=logout">Logout</a>
		<hr>
	</div>
	
	<div class="text-l"></div><br>
	
	<form action="UserController?action=search" method="post">
		<br><br>
		<label for="search"><b>Search here: </b></label><br>
		<input type="text" placeholder="Enter username" name="username" value="<c:out value='${search.username}'/>" size="40px"><br><br><br><br>
		<input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
		
		<center>
			<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
				<b>Search</b>
			</button>
		</center><br><br>
		<hr>
	</form>
</body>
</html>