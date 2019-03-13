<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="com.model.UserModel"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Login Project</title>
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	<link rel="stylesheet" href="css/style.css">
	
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="css/DataTable/dataTables.bootstrap.min.css">
	<link rel="stylesheet" href="css/datatable.css">
	<link rel="stylesheet" href="css/DataTable/responsive.bootstrap.min.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
</head>
<body>

<c:if test="${sessionScope.userDetails.userId ne null}">
	<div class="header">
		<div class="container">
		  <a href="<%=request.getContextPath()%>/AdminDataServlet" class="logo"><img class="logoHeader" src="image/logo.png"></a>
		  <div class="header-right">
		    <form action="RegistrationServlet"  method="post">
				<button class="btn btn-warning" name="btnValue" value="logout" type="submit"> Logout </button>
			</form>
		  </div>
		</div>
	</div>
</c:if>