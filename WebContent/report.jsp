<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
	<title>Problem Report page</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<center><h2>UTA Mac Facility Maintenance System</h2></center>
		<center><h2>Report a Problem</h2></center>

		<form action="MARController?action=report" method="post">
			<input type="hidden" name="reporter" value="<c:out value='${username}'/>"/>
			<div>
				<label for="name" placeholder = "Name">Name of the facility:</label>
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
				<input name="facilityNameError"  value="<c:out value='${errorMsgs.facilityNameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			</div>
			
			<div>
				<label for="urgency" placeholder ="urgency">urgency:</label>
				<input type="hidden" id="oldurgency" value="<c:out value='${MAR.urgency}'/>"/>
				<select name="urgency" id="urgency">
					<option>Unusable</option>
					<option>Major</option>
					<option>Medium</option>
					<option>Minor</option>
				</select>
				<input name="urgencyError"  value="<c:out value='${errorMsgs.urgencyError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			</div>
			
			<div>
				<label for="msg">Problem description:</label>
				<textarea id="msg" name="description" value="<c:out value='${MAR.description}'/>"></textarea>
				<input name="descriptionError"  value="<c:out value='${errorMsgs.descriptionError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			</div>
			
			<div class="button">
				<button type="submit">Submit</button>
			</div>
		</form>
	</div>
</body>

<script>
$(document).ready(function () {
	if($('#oldfacilityname').val() != ""){
		$('#facilityname').val($('#oldfacilityname').val());
	}
	
	if($('#oldurgency').val() != ""){
		$('#urgency').val($('#oldurgency').val());
	}
});
</script>
</html>
