<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>MAR table</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center>
			<header>
				<h2>UTA Mac Facility Maintenance System</h2>
			</header>
		</center>
		
		<h4> Repairer Home page</h4>
		<div align="right">
			<a href="UserController?action=home&role=Repairer&username=<c:out value='${username}'/>">Home</a>&nbsp;
			<a href="#">Update profile</a>
			<a href="MARController?action=request&username=<c:out value='${username}'/>">Request Repair Reservation</a>&nbsp;
			<a href="MARController?action=reserved&username=<c:out value='${username}'/>">View My Reserved Repairs</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		<hr>
		
		<form action="MARController?action=estimate" method="post">
			<input type="hidden" name="username" value="<c:out value='${username}'/>"/>
			
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
					<th> Reservation Time</th>
					<th></th>
				</tr>
				<tr>
					<td><c:out value='${MAR.facilitytype}'/></td>
					<td><c:out value='${MAR.facilityname}'/></td>
					<td><c:out value='${MAR.urgency}'/></td>
					<td><c:out value='${MAR.description}'/></td>
					<td><c:out value='${MAR.reporter}'/></td>
					<td><c:out value='${MAR.reportdate}'/></td>
					<td>
						<c:out value='${MAR.idx}'/>
						<input type="hidden" name="idx" value="<c:out value='${MAR.idx}'/>"/>
					</td>
					<td><c:out value='${MAR.repairer}'/></td>
					<td><c:out value='${MAR.assigndate}'/></td>
					<td>
						<select name="estimate">
							<c:forEach items="${estimates}" var="item" varStatus="status">
								<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<c:out value='${MAR.repairdate}'/><br>
						<c:out value='${MAR.starttime}'/> - <c:out value='${MAR.endtime}'/>
					</td>
					<td>
						<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 15px; padding-bottom: 15px; border-radius:200px;">
							<b>Update</b>
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>