<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
	<title>Result</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<style>
	table {
		font-family: arial, sans-serif;
		border-collapse: collapse;
		width: 100%;
	}
	
	td, th {
		border: 1px solid #dddddd;
		text-align: left;
		padding: 8px;
	}
</style>

<body>
	<div class="container">
		<center>
			<header>
				<h2>UTA Mac Facility Maintenance System</h2>
			</header>
		</center>
		
		<table>
			<tr>
				<th>Name of the facility</th>
				<th>Duration</th>
				<th>Interval</th>
			</tr>
			<tr>
				<td><c:out value='${facility.master}'/><c:out value='${facility.id}'/></td>
				<td><c:out value='${facility.duration}'/></td>
				<td><c:out value='${facility.interval}'/>s</td>
			</tr>
		</table><br><br>
		
		<table>
			<tr>
				<th>Date</th>
				<th>Time</th>
			</tr>
			
			<c:forEach items="${MARs}" var="item" varStatus="status">
			<tr>
				<td><c:out value='${item.repairdate}'/></td>
				<td><c:out value='${item.starttime}'/> - <c:out value='${item.endtime}'/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>