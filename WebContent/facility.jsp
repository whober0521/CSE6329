<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<body>
	<form action="FacilityController?action=add" method="post">
		<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br><br>
		
		Facility Type:<br>
		<select name="master">
			<c:forEach items="${types}" var="item" varStatus="status">
				<option value="<c:out value='${item.key}'/>"><c:out value='${item.value}'/></option>
			</c:forEach>
		</select><br>
		
		Number of room:<br>
		<input type="text" name="number" value="">
		<input name="numberError"  value="<c:out value='${errorMsgs.numberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br><br>
		
		Interval: <br>
		<select name="interval">
			<option value="30">30 minutes</option>
			<option value="60">1 hour</option>
			<option value="120">2 hours</option>
		</select><br><br>
		
		Duration: <br>
		<select name="duration">
			<option value="1">1 day</option>
			<option value="2">2 days</option>
			<option value="4">4 days</option>
			<option value="7">7 days</option>
		</select><br><br>
		
		Venue: <br>
		<select name="venue">
			<option>Indoor</option>
			<option>Outdoor</option>
		</select><br><br>
		
		<input type="submit" value="Submit">
	</form>
</body>
</html>