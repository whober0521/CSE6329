<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Reported problems</title>
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
		
		<h4> Facility Manager page</h4>
		<div align="right">
			<a href="manager.jsp">Home</a>&nbsp;
			<a href="#">View profile</a>&nbsp;
			<a href="#">Available facilities</a>&nbsp;
			<a href="#">Search facility</a>&nbsp;
			<a href="#">Add new facility</a>&nbsp;
			<a href="MARController?action=search">Reported problems</a>&nbsp;
			<a href="#">Repairers details</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<div class="text-l"></div><br>
		<form method="post" action="reportedproblem.html">
			<br><br>
			<label for="search"><b>MAR number:</b></label><br>
			<input type="text" name="search" placeholder="Enter MAR number" size="40px">
			<label for="date"><b>Date:</b></label>
			<input type="text" value="" name="date">
			<label for="time"><b>Time:</b></label>
			<input type="text" value="" name="time">
			<label for="facility type"><b>Facility type:</b></label>
			<input type="text" value="MR" name="facility type">
			<label for="facility name"><b>Facility name:</b></label>
			<input type="text" value="null" name="facility name">
			<label for="assigned to"><b>Assigned to:</b></label>
			<input type="text" value="null" name="assigned to">
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
					<b>Search</b>
				</button>
			</center><br><br>
			<hr>
		</form>
	</div>
</body>
</html>