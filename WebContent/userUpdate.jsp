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
			<a href="UserController?action=home&role=Admin&admin=<c:out value='${username}'/><c:out value='${admin}'/>">Home</a>&nbsp;
			<a href="UserController?action=search&admin=<c:out value='${username}'/><c:out value='${admin}'/>">Search user</a>&nbsp;
			<a href="UserController?action=logout">Logout</a>
			<hr>
		</div>
		
		<form action="UserController?action=update" method="post">
			<div class="container">
				<center><h1>Profile</h1></center><br>
				<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br>
				<input type="hidden" name="admin" value="<c:out value='${admin}'/>">
				
				<label for="username"><b>Username:</b></label><br>
				<input type="hidden" name="username" value="<c:out value='${update.username}'/>"/>
				<input type="text" value="<c:out value='${update.username}'/>" disabled><br>
				
				<label for="password"><b>Password:</b></label><br>
				<input type="password" placeholder="Enter your password" name="pwd" value="<c:out value='${update.password}'/>"><br>
				<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="role"><b>Role:</b></label>
				<select name="role" id="role">
					<c:forEach items="${roles}" var="item" varStatus="status">
					<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
				</c:forEach>		
				</select><br>
				
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
				
				<label for="address"><b>Address:</b></label><br>
				<input type="text" placeholder="Enter your street address" name="address" value="<c:out value='${update.address}'/>"><br>
				<input name="addressError"  value="<c:out value='${errorMsgs.addressError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="city"><b>City:</b></label>
				<input type="text" placeholder="Enter your city" name="city" value="<c:out value='${update.city}'/>"><br>
				<input name="cityError"  value="<c:out value='${errorMsgs.cityError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
				
				<label for="state"><b>State:</b></label>
				<select name="state" class="states order-alpha">
					<c:forEach items="${states}" var="item" varStatus="status">
						<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
					</c:forEach>
				</select><br>
				
				<center>
					<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
						<b>Update</b>
					</button>
				</center>
			</div>	
		</form>
	</div>
</body>
</html>