<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<div class="container">
		<div class="text-l">
			<center>
				<h1>Please Login</h1><br>
			</center>
		</div><br>
		
		<form action="UserController?action=login" method="post">
			<br><br>
			<label for="username"><b>Username: </b></label><br>
			<input type="text" name="username" placeholder="Enter your username" size="20" value="<c:out value='${user.username}'/>"><br><br>
			<input name="usernameError"  value="<c:out value='${errorMsgs.usernameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<label for="password"><b>Password: </b></label><br>
			<input type="password" name="password" placeholder="Enter your password" size="20"value="<c:out value='${user.password}'/>"><br><br><br><br>
			<input name="passwordError"  value="<c:out value='${errorMsgs.passwordError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
			
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
					<b>Sign in</b>
				</button>
			</center><br><br>
			
			<div class="container signin">
				<p>Not a member? <a href="UserController?action=register">Sign up</a>.</p>
			</div>
		</form>
	</div>
</body>
</html>