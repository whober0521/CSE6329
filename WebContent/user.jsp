<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<head>
	<title>Home page</title>
	<link rel="stylesheet" type="text/css" href="advse.css">
</head>

<body>
	<center>
		<header>
			<h2>UTA Mac Facility Maintenance System</h2>
		</header>
	</center>
	
	<h4> User Homescreen</h4>
	${user.getUserName()}
	<div class="container1">
		<div align="right">
			<a href="#">View profile</a>&nbsp;
			<a href="#">Update Profile</a>&nbsp;
			<a href="MAR_Form.html">Create Problem Report</a>&nbsp;
			<a href="#">Search Problem Reports</a>&nbsp;
			<a href="#">ViewSpecific Problem Report</a>&nbsp;
			<a href="logout.html">Logout</a>
			<hr>
		</div>
	</div>
</body>
</html>