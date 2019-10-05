<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<head>
	<title>Update Profile</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<form action="UserController?action=register" method="post">
		<div class="container">
			<center>
				<h1>Register</h1>
				<p>Please fill in this form to create an account.</p>
			</center><br>
						
			
			<label for="password"><b>Password:</b></label><br>
			<input type="password" placeholder="Enter your password" name="pwd" value="<c:out value='${user.password}'/>"><br>
			<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="role"><b>Role:</b></label>
			<input type="hidden" id="oldrole" value="<c:out value='${user.role}'/>"/>
			<select name="role" id="role">
				<option value="U">User</option>
				<option value="F">Facility Manager</option>
				<option value="A">Admin</option>
				<option value="R">Repairer</option>			
			</select><br>

			<label for="utaid"><b>UTA ID:</b></label><br>
			<input type="text" placeholder="Enter your UTA ID" name="utaid" value="<c:out value='${user.utaid}'/>"><br>
			<input name="utaidError"  value="<c:out value='${errorMsgs.utaidError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="firstname"><b>First Name:</b></label><br>
			<input type="text" placeholder="Enter your first name" name="fname" value="<c:out value='${user.fname}'/>"><br>
			<input name="fnameError"  value="<c:out value='${errorMsgs.fnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="lastname"><b>Last Name:</b></label><br>
			<input type="text" placeholder="Enter your last name" name="lname" value="<c:out value='${user.lname}'/>"><br>
			<input name="lnameError"  value="<c:out value='${errorMsgs.lnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="email"><b>Email:</b></label><br>
			<input type="text" placeholder="Enter your email id" name="email" value="<c:out value='${user.email}'/>"><br>
			<input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="phone"><b>Phone:</b></label><br>
			<input type="text" placeholder="Enter your phone number" name="phone" value="<c:out value='${user.phone}'/>"><br>
			<input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="address"><b>Address:</b></label><br>
			<input type="text" placeholder="Enter your street address" name="address" value="<c:out value='${user.address}'/>"><br>
			<input name="addressError"  value="<c:out value='${errorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="city"><b>City:</b></label>
			<input type="text" placeholder="Enter your city" name="city" value="<c:out value='${user.city}'/>"><br>
			<input name="cityError"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="state"><b>State:</b></label>
			<select name="state" class="states order-alpha">
				<option value="AL">Alabama</option>
				<option value="AK">Alaska</option>
				<option value="AZ">Arizona</option>
				<option value="AR">Arkansas</option>
				<option value="CA">California</option>
				<option value="CO">Colorado</option>
				<option value="CT">Connecticut</option>
				<option value="DE">Delaware</option>
				<option value="DC">District Of Columbia</option>
				<option value="FL">Florida</option>
				<option value="GA">Georgia</option>
				<option value="HI">Hawaii</option>
				<option value="ID">Idaho</option>
				<option value="IL">Illinois</option>
				<option value="IN">Indiana</option>
				<option value="IA">Iowa</option>
				<option value="KS">Kansas</option>
				<option value="KY">Kentucky</option>
				<option value="LA">Louisiana</option>
				<option value="ME">Maine</option>
				<option value="MD">Maryland</option>
				<option value="MA">Massachusetts</option>
				<option value="MI">Michigan</option>
				<option value="MN">Minnesota</option>
				<option value="MS">Mississippi</option>
				<option value="MO">Missouri</option>
				<option value="MT">Montana</option>
				<option value="NE">Nebraska</option>
				<option value="NV">Nevada</option>
				<option value="NH">New Hampshire</option>
				<option value="NJ">New Jersey</option>
				<option value="NM">New Mexico</option>
				<option value="NY">New York</option>
				<option value="NC">North Carolina</option>
				<option value="ND">North Dakota</option>
				<option value="OH">Ohio</option>
				<option value="OK">Oklahoma</option>
				<option value="OR">Oregon</option>
				<option value="PA">Pennsylvania</option>
				<option value="RI">Rhode Island</option>
				<option value="SC">South Carolina</option>
				<option value="SD">South Dakota</option>
				<option value="TN">Tennessee</option>
				<option value="TX">Texas</option>
				<option value="UT">Utah</option>
				<option value="VT">Vermont</option>
				<option value="VA">Virginia</option>
				<option value="WA">Washington</option>
				<option value="WV">West Virginia</option>
				<option value="WI">Wisconsin</option>
				<option value="WY">Wyoming</option>
			</select><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;"><b>Update</b></button>
			</center>
		</div>
	</form>
</body>
</html>
