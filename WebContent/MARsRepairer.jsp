<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<head>
</head>
<style>
	table {
		font-family: arial, sans-serif;
		border-collapse: collapse;
		width: 100%;
	}
		
	td, th {
		border: 1px solid #dddddd;
		text-align: left;
		padding: 8px;
	}
</style>

<body>
	<table>
		<tr>
			<th colspan='3'>MAR number</th>
		</tr>
		
		<c:forEach items="${MARs}" var="item" varStatus="status">
		<tr>
			<td><c:out value='${item.idx}'/></td>
			<td><a href="MARController?action=MARRepairer&idx=<c:out value='${item.idx}'/>&username=<c:out value='${username}'/>">View</a></td>
			<td><a href="MARController?action=cancel&idx=<c:out value='${item.idx}'/>&username=<c:out value='${username}'/>">Cancel</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>