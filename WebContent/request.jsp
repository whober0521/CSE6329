<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<body>
	<form action="MARController?action=request" method="post">
		<input name="errMsg"  value="<c:out value='${errorMsgs.errorMsg}'/>" type="text"  style ="background-color: white; color: red; border: none; width:800px" disabled="disabled"><br>
		<input type="hidden" name="repairer" value="<c:out value='${username}'/>"/>
		
		Search here:<br>
		<select name="facilityname">
			<c:forEach items="${names}" var="item" varStatus="status">
				<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
			</c:forEach>
		</select><br>
		<input name="nameError"  value="<c:out value='${errorMsgs.nameError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br>
		
		Date:<br>
		<input type="date" name="repairdate" value="<c:out value='${today}'/>"><br>
		Time:<br>
		<select name="starttime">
			<c:forEach items="${now}" var="item" varStatus="status">
				<option <c:out value='${item.value}'/>><c:out value='${item.key}'/></option>
			</c:forEach>	
		</select><br>
		<input name="datetimeError"  value="<c:out value='${errorMsgs.datetimeError}'/>" type="text"  style ="background-color: white; color: red; border: none; width: 800px"  disabled="disabled" maxlength="60"><br><br>
		
		<input type="submit" value="Search">
	</form>
</body>
</html>