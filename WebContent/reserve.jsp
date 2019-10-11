<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Result</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center>
			<header>
				<h2>UTA Mac Facility Maintenance System</h2>
			</header>
		</center>
	
		<table>
			<tr><th>Name of the facility</th></tr>
			<tr><td><c:out value='${facility}'/></td></tr>
		</table><br>
		
		<table>
			<tr>
				<th>Date</th>
				<th>Time</th>
				<th>Button</th>
			</tr>
			
			<c:forEach items="${MARs}" var="item" varStatus="status">
			<tr>
				<td><c:out value='${item.repairdate}'/></td>
				<td><c:out value='${item.starttime}'/> - <c:out value='${item.endtime}'/></td>
				<td>
					<a href="MARController?action=reserve&username=<c:out value='${username}'/>&idx=<c:out value='${item.idx}'/>
					&repairdate=<c:out value='${item.repairdate}'/>&starttime=<c:out value='${item.starttime}'/>&endtime=<c:out value='${item.endtime}'/>">
						Book
					</a>
				</td>
			</tr>
			</c:forEach>
		</table>
	</body>
</html>