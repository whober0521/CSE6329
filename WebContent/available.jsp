<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Available facilities</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center><h2>UTA Mac Facility Maintenance System</h2></center>
		
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
		
		<form action="MARController?action=available" method="post">
			<br><br>
			<input type="hidden" name="username" value="<c:out value='${username}'/>">
			
			<label for="search"><b>Search here: </b></label>
			<select name="facilityname">
				<c:forEach items="${names}" var="item" varStatus="status">
					<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
				</c:forEach>
			</select><br>
			
			<label for="date"><b>Date:</b></label>
			<input type="date" name="repairdate" value="<c:out value='${today}'/>"><br>
			
			<label for="time"><b>Time:</b></label>
			<select name="starttime">
				<c:forEach items="${now}" var="item" varStatus="status">
					<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
				</c:forEach>
			</select><br><br>
			
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