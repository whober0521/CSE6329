<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<head>
	<title>Registration</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<form action="RegisterController" method="post">
		<div class="container">
			<center>
				<h1>Register</h1>
				<p>Please fill in this form to create an account.</p>
			</center><br>
			
			<label for="username"><b>Username:</b></label><br>
			<input type="text" placeholder="Enter your username" name="username"><br>
			<label for="password"><b>Password:</b></label><br>
			<input type="password" placeholder="Enter your password" name="pwd"><br>
			
			<label for="role"><b>Role:</b></label>
			<select name="role">
				<option value="U">User</option>
				<option value="F">Facility Manager</option>
				<option value="A">Admin</option>
				<option value="R">Repairer</option>			
			</select><br>
			
			<label for="utaid"><b>UTA ID:</b></label><br>
			<input type="text" placeholder="Enter your UTA ID" name="utaid"><br>
			<label for="firstname"><b>First Name:</b></label><br>
			<input type="text" placeholder="Enter your first name" name="fname"><br>
			<label for="lastname"><b>Last Name:</b></label><br>
			<input type="text" placeholder="Enter your last name" name="lname"><br>
			<label for="email"><b>Email:</b></label><br>
			<input type="text" placeholder="Enter your email id" name="email"><br>
			<label for="phone"><b>Phone:</b></label><br>
			<input type="text" placeholder="Enter your phone number" name="phone"><br>
			
			<input type="hidden" name="country" id="countryId" value="US"/>
			<label for="state"><b>State:</b></label>
			<select name="state" class="states order-alpha" id="stateId">
				<option value="">Select State</option>
			</select><br>
			<label for="city"><b>City:</b></label>
			<select name="city" class="cities order-alpha" id="cityId">
				<option value="">Select City</option>
			</select><br>
			<label for="address"><b>Address:</b></label><br>
			<input type="text" placeholder="Enter your street address" name="address"><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;"><b>Register</b></button>
			</center>
		</div>
		
		<div class="container signin">
			<p>Already have an account? <a href="index.jsp">Sign in</a>.</p>
		</div>
	</form>
</body>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//geodata.solutions/includes/statecity.js"></script>
</html>