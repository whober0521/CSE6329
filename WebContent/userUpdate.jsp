<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>User profile</title>
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
	
	<h4> Admin page</h4>
	<div align="right">
		<a href="admin.jsp">Home</a>&nbsp;
		<a href="#">View profile</a>&nbsp;
		<a href="UserController?action=search">Search user</a>&nbsp;
		<a href="UserController?action=logout">Logout</a>
		<hr>
	</div>
	
	<form action="UserController?action=update" method="post">
		<div class="container">
			<center><h1>Profile</h1></center><br>
			
			<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
			
			<label for="username"><b>Username:</b></label><br>
			<input type="hidden" name="username" value="<c:out value='${update.username}'/>"/>
			<input type="text" value="<c:out value='${update.username}'/>" disabled><br>
			
			<label for="password"><b>Password:</b></label><br>
			<input type="password" placeholder="Enter your password" name="pwd" value="<c:out value='${update.password}'/>"><br>
			<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="role"><b>Role:</b></label>
			<input type="hidden" id="oldrole" value="<c:out value='${update.role}'/>"/>
			<select name="role" id="role">
				<option value="U">User</option>
				<option value="F">Facility Manager</option>
				<option value="A">Admin</option>
				<option value="R">Repairer</option>			
			</select><br>
			<input name="roleError"  value="<c:out value='${errorMsgs.roleError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="utaid"><b>UTA ID:</b></label><br>
			<input type="text" placeholder="Enter your UTA ID" name="utaid" value="<c:out value='${update.utaid}'/>"><br>
			<input name="utaidError"  value="<c:out value='${errorMsgs.utaidError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			
			<label for="firstname"><b>First Name:</b></label><br>
			<input type="text" placeholder="Enter your first name" name="fname" value="<c:out value='${update.fname}'/>"><br>
			<input name="fnameError"  value="<c:out value='${errorMsgs.fnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="lastname"><b>Last Name:</b></label><br>
			<input type="text" placeholder="Enter your last name" name="lname" value="<c:out value='${update.lname}'/>"><br>
			<input name="lnameError"  value="<c:out value='${errorMsgs.lnameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="email"><b>Email:</b></label><br>
			<input type="text" placeholder="Enter your email id" name="email" value="<c:out value='${update.email}'/>"><br>
			<input name="emailError"  value="<c:out value='${errorMsgs.emailError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="phone"><b>Phone:</b></label><br>
			<input type="text" placeholder="Enter your phone number" name="phone" value="<c:out value='${update.phone}'/>"><br>
			<input name="phoneError"  value="<c:out value='${errorMsgs.phoneError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<input type="hidden" name="country" id="countryId" value="US"/>
			
			<label for="state"><b>State:</b></label>
			<input type="hidden" id="oldstate" value="<c:out value='${update.state}'/>"/>
			<select name="state" class="states order-alpha" id="stateId">
				<option value="">Select State</option>
			</select><br>
			<input name="stateError"  value="<c:out value='${errorMsgs.stateError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="city"><b>City:</b></label>
			<input type="hidden" id="oldcity" value="<c:out value='${update.city}'/>"/>
			<select name="city" class="cities order-alpha" id="cityId">
				<option value="">Select City</option>
			</select><br>
			<input name="cityError"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="address"><b>Address:</b></label><br>
			<input type="text" placeholder="Enter your street address" name="address" value="<c:out value='${update.address}'/>"><br>
			<input name="addressError"  value="<c:out value='${errorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
					<b>Update</b>
				</button>
			</center>
		</div>
	</form>
</body>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//geodata.solutions/includes/statecity.js"></script>
<script>
$(document).ready(function () {
	if($('#oldrole').val() != ""){
		$('#role').val($('#oldrole').val());
	}
	
	if($('#oldstate').val() != ""){
		setTimeout(function(){
			$('#stateId').val($('#oldstate').val());
			$('#stateId').change();

			if($('#oldcity').val() != ""){
				
				setTimeout(function(){
					$('#cityId').val($('#oldcity').val());
				}, 2000);
			}
		}, 2000);
	}
});
</script>
</html>