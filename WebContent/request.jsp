<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<body>
	<form action="/action_page.php">
		Search here:<br>
		<select name="facilityname">
			<c:forEach items="${names}" var="item" varStatus="status">
				<option><c:out value='${item}'/></option>
			</c:forEach>
		</select><br>
		
		Date:<br>
		<input type="date" name="availabledate" value="<c:out value='${MAR.assigndate}'/>"><br><br>
		
		Time:<br>
		<select name="availabletime">
			<option>00:00</option>
			<option>01:00</option>
			<option>02:00</option>
			<option>03:00</option>
			<option>04:00</option>
			<option>05:00</option>
			<option>06:00</option>
			<option>07:00</option>
			<option>08:00</option>
			<option>09:00</option>
			<option>10:00</option>
			<option>11:00</option>
			<option>12:00</option>
			<option>13:00</option>
			<option>14:00</option>
			<option>15:00</option>
			<option>16:00</option>
			<option>17:00</option>
			<option>18:00</option>
			<option>19:00</option>
			<option>20:00</option>
			<option>21:00</option>
			<option>22:00</option>
			<option>23:00</option>
		</select><br>
		
		<input type="submit" value="Search">
	</form>
</body>
</html>