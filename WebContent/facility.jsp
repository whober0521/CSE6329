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
				<option <c:out value='${item.value}'/> value="<c:out value='${item.key.master}'/>"><c:out value='${item.key.venue}'/></option>
			</c:forEach>
		</select><br>
		
		Number of room:<br>
		<input type="text" name="number" value="">
		<input name="numberError"  value="<c:out value='${errorMsgs.numberError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br><br>
		
		Interval: <br>
		<select name="interval">
			<c:forEach items="${intervals}" var="item" varStatus="status">
				<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
			</c:forEach>
		</select><br><br>
		
		Duration: <br>
		<select name="duration">
			<c:forEach items="${durations}" var="item" varStatus="status">
				<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
			</c:forEach>
		</select><br><br>
		
		Venue: <br>
		<select name="venue">
			<c:forEach items="${venues}" var="item" varStatus="status">
				<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
			</c:forEach>
		</select><br><br>
		
		<input type="submit" value="Submit">
	</form>
</body>
</html>