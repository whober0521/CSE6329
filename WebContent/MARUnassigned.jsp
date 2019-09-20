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
		<hr>
		
		<input name="repairerError"  value="<c:out value='${errorMsgs.repairerError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>

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
				<th></th>
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
					<td>
						<c:out value='${item.idx}'/>
						<input type="hidden" name="idx" value="<c:out value='${item.idx}'/>"/>
					</td>
					<td><input type="text" name="repairer"></td>
					<td></td>
					<td></td>
					<td>
						<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 15px; padding-bottom: 15px; border-radius:200px;">
							<b>Update</b>
						</button>
					</td>
				</form>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>