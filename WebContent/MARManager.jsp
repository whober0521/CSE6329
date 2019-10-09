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
			<a href="#">Update profile</a>&nbsp;
			<a href="#">Search facility</a>&nbsp;
			<a href="#">Add new facility</a>&nbsp;
			<a href="#">View Assignment Problems</a>&nbsp;
			<a href="MARController?action=unassigned">View Unassigned Problems</a>&nbsp;
			<a href="#">Repairers details</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		<hr>
		
		<table>
			<tr>
				<th>Facility Name</th>
				<th>Urgency</th>
				<th>Description</th>
				<th>Reported By</th>
				<th>Reported Date</th>
				<th>MAR number</th>
				<th>Assigned to</th>
				<th>Assigned date</th>
				<th>Estimate of repair</th>
				<th></th>
			</tr>
			<tr>
				<form action="MARController?action=assign" method="post">
					<td><c:out value='${MAR.facility}'/></td>
					<td>
						<select name="urgency">
							<option>Unusable</option>
							<option>Major</option>
							<option>Medium</option>
							<option>Minor</option>
						</select>
					</td>
					<td><c:out value='${MAR.description}'/></td>
					<td><c:out value='${MAR.reporter}'/></td>
					<td><c:out value='${MAR.reportdate}'/></td>
					<td>
						<c:out value='${MAR.idx}'/>
						<input type="hidden" name="idx" value="<c:out value='${MAR.idx}'/>"/>
					</td>
					<td>
						<select name="repairer">
							<c:forEach items="${repairers}" var="item" varStatus="status">
								<option><c:out value='${item}'/></option>
							</c:forEach>
						</select>
					</td>
					<td><c:out value='${MAR.assigndate}'/></td>
					<td>
						<select name="estimate">
							<option>30 mins</option>
							<option>1 hour</option>
							<option>2 hours</option>
							<option>4 hours</option>
							<option>1 day</option>
							<option>2 day</option>
							<option>4 day</option>
							<option>7 day</option>	
						</select>
					</td>
					<td>
						<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 15px; padding-bottom: 15px; border-radius:200px;">
							<b>Update</b>
						</button>
					</td>
				</form>
			</tr>
		</table>
	</div>
</body>
</html>