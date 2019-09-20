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
			<a href="fm_homepage.html">Home</a>&nbsp;
			<a href="#">View profile</a>&nbsp;
			<a href="#">Available facilities</a>&nbsp;
			<a href="#">Search facility</a>&nbsp;
			<a href="#">Add new facility</a>&nbsp;
			<a href="viewproblems_fm.html">Reported problems</a>&nbsp;
			<a href="#">Repairers details</a>&nbsp;
			<a href="logout.html">Logout</a>
			<hr>
		</div>
		<hr>
		
		<form method="post">
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
				
				<c:forEach items="${COMPANIES}" var="item" varStatus="status">
				<tr>
					<td>Multipurpose rooms</td>
					<td>MR 3</td>
					<td>Minor</td>
					<td>The lock of the door in the room is broken</td>
					<td>XYZ</td>
					<td>9/17/2019</td>
					<td>001</td>
					<td>null</td>
					<td>9/18/2019</td>
					<td>One hour</td>
					<td> <a href="/company_management/CompanyController?action=listSpecificCompany&id=${item.idcompany}">View</a></td>
				</tr>
				
	
			<td class="myTableCell" style="width: 20px; text-align: center">
			<input type="radio" id="radioCompany${status.count}" name="radioCompany" value="${status.count}"></td> 	
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.idcompany}" /></td>
			<td class="myTableCell" style="padding-right: 35px; "><c:out value="${item.phone}" /></td>
			<td class="myTableCell" style="padding-right: 20px; "><c:out value="${item.company_name}" /></td>
			<td class="myTableCell" style="padding-right: 30px; "><c:out value="${item.email}" /></td>


		</c:forEach>
				
	
			</table><br><br><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 15px; padding-bottom: 15px; border-radius:200px;">
					<b>Update</b>
				</button>
			</center><br><br>
		</form>
	</div>
</body>
</html>