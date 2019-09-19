<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
	<title>Problem Report page</title>
	<link rel="stylesheet" type="text/css" href="homepage.css">
</head>

<body>
	<div class="container">
		<center><h2>UTA Mac Facility Maintenance System</h2></center>
		<center><h2>Report a Problem</h2></center>
		
		<form action="" method="post">
			<div>
				<label for="name" placeholder = "Name">Name of the facility:</label>
				<select name="facility">
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
			</div>
			
			<div>
				<label for="urgency" placeholder ="urgency">urgency:</label>
				<select name="urgency">
					<option>Unusable</option>
					<option>Major</option>
					<option>Medium</option>
					<option>Minor</option>
				</select>
			</div>
			
			<div>
				<label for="msg">Problem description:</label>
				<textarea id="msg" name="description"></textarea>
			</div>
			
			<div class="button">
				<button type="submit">Submit</button>
			</div>
		</form>
	</div>
</body>
</html>