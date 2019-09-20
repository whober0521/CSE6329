<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Reported problem</title>
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
		
		<h4> Repairer Home page</h4>
		<div align="right">
			<a href="repairer.jsp">Home</a>&nbsp;
			<a href="#">Request Repair Reservation</a>&nbsp;
			<a href="MARAssignedSearch.jsp">View My Reserved Repairs</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		<hr>

		<table>
			<tr>
				<th>Facility type</th>
				<th>Name</th>
				<th>Urgency</th>
				<th>Description</th>
				<th>Reported By</th>
				<th>Date</th>
				<th>MAR number</th>
				<th>Assigned to</th>
				<th>Assigned date</th>
				<th>Estimate of repair</th>
			</tr>
			
			<c:forEach items="${MARs}" var="item" varStatus="status">
			<tr>
				<form action="MARController?action=assign" method="post">
					<td><c:out value='${item.facilitytype}'/></td>
					<td><c:out value='${item.facilityname}'/></td>
					<td><c:out value='${item.urgency}'/></td>
					<td><c:out value='${item.description}'/></td>
					<td><c:out value='${item.reporter}'/></td>
					<td><c:out value='${item.reportdate}'/></td>
					<td><c:out value='${item.idx}'/></td>
					<td><c:out value='${item.repairer}'/></td>
					<td><c:out value='${item.assigndate}'/></td>
					<td></td>
				</form>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>