<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>View my assigned repairs</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
</head>

<body>
	<div class="container">
		<center>
			<h2>UTA Mac Facility Maintenance System</h2>
		</center>
		
		<h4> Repairer page</h4>
		<div align="right">		
			<a href="repairer.jsp">Home</a>&nbsp;
			<a href="#">Request Repair Reservation</a>&nbsp;
			<a href="MARAssignedSearch.jsp">View My Reserved Repairs</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<div class="text-l"></div><br>
		
		<form action="MARController?action=search_r" method="post">
			<input type="hidden" name="repairer" value="<c:out value='${username}'/>"/>
			<br><br>

			<label for="date"><b>Date:</b></label>
			<input type="text" id="reportdate" name="reportdate" value="<c:out value='${MAR.reportdate}'/>"><br>
			<input name="reportDateError"  value="<c:out value='${errorMsgs.reportDateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="time"><b>Time:</b></label>
			<input type="hidden" id="oldtime" value="<c:out value='${MAR.reporttime}'/>"/>
			<select name="reporttime" id="reporttime">
				<option>00:00</option>
				<option>01:00</option>
				<option>02:00</option>
				<option>03:00</option>
				<option>04:00</option>
				<option>05:00</option>
				<option>06:00</option>
				<option>07:00</option>
				<option>08:00</option>
				<option>09:00</option>
				<option>10:00</option>
				<option>11:00</option>
				<option>12:00</option>
				<option>13:00</option>
				<option>14:00</option>
				<option>15:00</option>
				<option>16:00</option>
				<option>17:00</option>
				<option>18:00</option>
				<option>19:00</option>
				<option>20:00</option>
				<option>21:00</option>
				<option>22:00</option>
				<option>23:00</option>
			</select>
			<input name="reportTimeError"  value="<c:out value='${errorMsgs.reportTimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>

			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
					<b>Search</b>
				</button>
			</center><br><br>
			<hr>
		</form>
	</div>
</body>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
$(document).ready(function () {
	$( "#reportdate" ).datepicker();
	
	if($('#oldtime').val() != ""){
		$('#reporttime').val($('#oldtime').val());
	}
});
</script>
</html>
