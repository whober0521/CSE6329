<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
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
		
		<form method="post" action="HomeController">
			<br><br>
			<label for="username"><b>Username: </b></label><br>
			<input type="text" name="email" placeholder="Enter your username" size="20"><br><br>
			<label for="password"><b>Password: </b></label><br>
			<input type="text" name="password" placeholder="Enter your password" size="20"><br><br><br><br>
			<center>
				<button type="submit" style="padding-right: 50px; padding-left: 50px; padding-top: 25px; padding-bottom: 25px; border-radius:200px;">
					<b>Sign in</b>
				</button>
			</center><br><br>
			
			<div class="container signin">
				<p>Not a member? <a href="signup.jsp">Sign up</a>.</p>
			</div>
		</form>
	</div>
</body>
</html>