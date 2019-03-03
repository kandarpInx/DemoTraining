<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<c:choose>
	<c:when test="${sessionScope.userDetails.role eq 'user'}">
		<title>User Home Page</title>
	</c:when>
	<c:otherwise>
		<title>Admin Home Page</title>
	</c:otherwise>
</c:choose>
<title>User Page</title>
</head>
<body>
	<c:choose>

	<c:when test="${sessionScope.userDetails.role eq 'admin'}">

		Welcome, ${sessionScope.userDetails.firstName}
	
		<br> Have a great day Adminji...
		<br> <br>
		<form action="RegistrationServlet" method="post">
			<button type="submit" name="btnValue" value="retrieveData"> Show details </button>
		</form>
		<br> <br>
		
		<form action="LogoutServlet" method="post">
			<button type="submit" value="submit"> Logout </button>
		</form>
		
	</c:when>
	
	<c:when test="${sessionScope.userDetails.role eq 'user'}">

		Welcome, ${sessionScope.userDetails.firstName}
	
		<br> Normal User...
		<br> <br>
		
		<form action="RegistrationServlet" method="post">
			<button type="submit" name="btnValue" value="retrieveData"> Show details </button>
		</form>
		
		<br> <br>
		
		<form action="LogoutServlet" method="post">
			<button type="submit" value="submit"> Logout </button>
		</form>
		
	</c:when>
	
	<c:otherwise>
		<jsp:forward page="index.jsp"></jsp:forward>
	</c:otherwise>
	
	</c:choose>
	
	
</body>
</html>