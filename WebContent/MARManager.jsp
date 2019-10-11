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
			<a href="UserController?action=home&role=Facility Manager&username=<c:out value='${username}'/>">Home</a>&nbsp;
			<a href="#">Update profile</a>&nbsp;
			<a href="FacilityController?action=search">Search facility</a>&nbsp;
			<a href="FacilityController?action=add">Add new facility</a>&nbsp;
			<a href="MARController?action=assigned&username=<c:out value='${username}'/>">View Assignment Problems</a>&nbsp;
			<a href="MARController?action=unassigned&username=<c:out value='${username}'/>">View Unassigned Problems</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		<hr>
		
		<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br>
		<input name="urgencyError"  value="<c:out value='${errorMsgs.urgencyError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
		<input name="repairerError"  value="<c:out value='${errorMsgs.repairerError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
		<input name="estimateError"  value="<c:out value='${errorMsgs.estimateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
					
		<table>
			<tr>
				<th>Facility Type</th>
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
					<td><c:out value='${MAR.facilitytype}'/></td>
					<td><c:out value='${MAR.facilityname}'/></td>
					<td>
						<select name="urgency">
							<option></option>
							
							<c:forEach items="${urgencies}" var="item" varStatus="status">
								<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
							</c:forEach>
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
							<option></option>
							
							<c:forEach items="${repairers}" var="item" varStatus="status">
								<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
							</c:forEach>
						</select>
					</td>
					<td><c:out value='${MAR.assigndate}'/></td>
					<td>
						<select name="estimate">
							<option></option>
							
							<c:forEach items="${estimates}" var="item" varStatus="status">
								<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
							</c:forEach>
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