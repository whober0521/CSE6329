<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<head>
	<title>Registration</title>
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
			
			<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br>
			<label for="username"><b>Username:</b></label><br>
			<input type="text" placeholder="Enter your username" name="username" value="<c:out value='${user.username}'/>"><br>
			<input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
						
			
			<label for="password"><b>Password:</b></label><br>
			<input type="password" placeholder="Enter your password" name="pwd" value="<c:out value='${user.password}'/>"><br>
			<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="role"><b>Role:</b></label>
			<select name="role" id="role">
				<option>User</option>
				<option>Facility Manager</option>
				<option>Admin</option>
				<option>Repairer</option>			
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
				<option>Alabama</option>
				<option>Alaska</option>
				<option>Arizona</option>
				<option>Arkansas</option>
				<option>California</option>
				<option>Colorado</option>
				<option>Connecticut</option>
				<option>Delaware</option>
				<option>District Of Columbia</option>
				<option>Florida</option>
				<option>Georgia</option>
				<option>Hawaii</option>
				<option>Idaho</option>
				<option>Illinois</option>
				<option>Indiana</option>
				<option>Iowa</option>
				<option>Kansas</option>
				<option>Kentucky</option>
				<option>Louisiana</option>
				<option>Maine</option>
				<option>Maryland</option>
				<option>Massachusetts</option>
				<option>Michigan</option>
				<option>Minnesota</option>
				<option>Mississippi</option>
				<option>Missouri</option>
				<option>Montana</option>
				<option>Nebraska</option>
				<option>Nevada</option>
				<option>New Hampshire</option>
				<option>New Jersey</option>
				<option>New Mexico</option>
				<option>New York</option>
				<option>North Carolina</option>
				<option>North Dakota</option>
				<option>Ohio</option>
				<option>Oklahoma</option>
				<option>Oregon</option>
				<option>Pennsylvania</option>
				<option>Rhode Island</option>
				<option>South Carolina</option>
				<option>South Dakota</option>
				<option>Tennessee</option>
				<option>Texas</option>
				<option>Utah</option>
				<option>Vermont</option>
				<option>Virginia</option>
				<option>Washington</option>
				<option>West Virginia</option>
				<option>Wisconsin</option>
				<option>Wyoming</option>
			</select><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;"><b>Register</b></button>
			</center>
		</div>
		
		<div class="container signin">
			<p>Already have an account? <a href="index.jsp">Sign in</a>.</p>
		</div>
	</form>
</body>
</html>