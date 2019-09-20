<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Reported problems</title>
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
		
		<h4> Facility Manager page</h4>
		<div align="right">
			<a href="manager.jsp">Home</a>&nbsp;
			<a href="#">View profile</a>&nbsp;
			<a href="#">Available facilities</a>&nbsp;
			<a href="#">Search facility</a>&nbsp;
			<a href="#">Add new facility</a>&nbsp;
			<a href="MARUnassignedSearch.jsp">Reported problems</a>&nbsp;
			<a href="#">Repairers details</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<div class="text-l"></div><br>
		<form action="MARController?action=search_fm" method="post">
			<br><br>
			<label for="search"><b>MAR number:</b></label><br>
			<input type="text" name="idx" placeholder="Enter MAR number" size="40px" value="<c:out value='${MAR.idx}'/>"><br>
			<input name="idxError"  value="<c:out value='${errorMsgs.idxError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
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
			
			<label for="facility type"><b>Facility type:</b></label>
			<input type="hidden" id="oldfacilitytype" value="<c:out value='${MAR.facilitytype}'/>"/>
			<select name="facilitytype" id="facilitytype">
				<option>MR 1-4</option>
				<option>IBBC 1-5</option>
				<option>IVBC 1-9</option>
				<option>SCG</option>
				<option>RBC 1-5</option>
				<option>BMC 1-10</option>
				<option>TT1</option>
				<option>CR 1-5</option>
				<option>OVBC 1-2</option>
				<option>OBBC 1-2</option>
			</select>
			<input name="facilityTypeError"  value="<c:out value='${errorMsgs.facilityTypeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="facility name"><b>Facility name:</b></label>
			<input type="hidden" id="oldfacilityname" value="<c:out value='${MAR.facilityname}'/>"/>
			<select name="facilityname" id="facilityname">
				<option>Multipurpose rooms</option>
				<option>5 Indoor basketball courts</option>
				<option>9 Volleyball courts</option>
				<option>Indoor soccer gymnasium</option>
				<option>5 Racquetball courts</option>
				<option>10 Badminton courts</option>
				<option>Table Tennis</option>
				<option>Indoor soccer gymnasium</option>
				<option>Table Tennis</option>
				<option>2 Outdoor Basketball Courts</option>
			</select>
			<input name="facilityNameError" value="<c:out value='${errorMsgs.facilityNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="assigned to"><b>Assigned to:</b></label>
			<input type="text" name="repairer" value="<c:out value='${MAR.repairer}'/>"><br>
			<input name="repairerError"  value="<c:out value='${errorMsgs.repairerError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
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

	if($('#oldfacilitytype').val() != ""){
		$('#facilitytype').val($('#oldfacilitytype').val());
	}
	
	if($('#oldfacilityname').val() != ""){
		$('#facilityname').val($('#oldfacilityname').val());
	}
});
</script>
</html>