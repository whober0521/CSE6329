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
				<select name="facility">
					<c:forEach items="${facilities}" var="item" varStatus="status">
						<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
					</c:forEach>
				</select>
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
</html>