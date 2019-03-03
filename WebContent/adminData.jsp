<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Data</title>
</head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="css/datatable.css" rel="stylesheet">
<body>
	<!---------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	----------------------------------------------Admin Table--------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------------------  -->

	<c:if test="${sessionScope.userDetails.role eq 'admin'}">
		<h1 align="center"> All Data </h1>
		<br> <br>
		<div class="container">
			<div class="row">
				<table id="example" class="table table-striped table-bordered" style="width: 100%">
					
					<thead>
						<tr>
							<th>User Id</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Birth Date</th>
							<th>Email Id</th>
							<th>Gender</th>
							<th>Contact No</th>
							<th>Languages</th>
							<th>Password</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${data}" var="data">
							<tr>
								<td><c:out value="${data.userId}" /></td>
								<td><c:out value="${data.firstName}" /></td>
								<td><c:out value="${data.lastName}" /></td>
								<td><c:out value="${data.dateOfBirth}" /></td>
								<td><c:out value="${data.emailId}" /></td>
								<td><c:out value="${data.gender}" /></td>
								<td><c:out value="${data.contactNo}" /></td>
								<td><c:out value="${data.languages}" /></td>
								<td><c:out value="${data.password}" /></td>
								<td>
									<form action="RegistrationServlet" method="post">
										<input type="hidden" name="userId" value="${data.userId}" /> 
										<button type="submit" name="btnValue" value="update1"> 
											Update
										</button> 
									</form>
								</td>
								<td> <form action="RegistrationServlet" method="post">
										<input type="hidden" name="userId" value="${data.userId}" /> 
										<button type="submit" name="btnValue" value="delete" onclick="return confirm('Are you sure?');"> 
											Remove
										</button> 
									</form>
								</td>
							</tr>
						</c:forEach>
						</tbody>
						
				</table>
				</div>
				<br> <br>
			<a href="<%=request.getContextPath()%>/home.jsp">Back</a>
				</div>
				
	</c:if>
	<!---------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	----------------------------------------------User Table---------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------------------  -->
	<c:if test="${sessionScope.userDetails.role eq 'user'}">
	<h1 align="center"> ${sessionScope.userDetails.firstName}'s Data </h1>
	<div class="container">
			<div class="row">
			
			<table align="center" border=2>
				<tr>
					<th>User Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Birth Date</th>
					<th>Email Id</th>
					<th>Gender</th>
					<th>Contact No</th>
					<th>Languages</th>
					<th>Password</th>
					<th> </th>
				</tr>
				<c:forEach items="${data}" var="data">
					<tr>
						<td><c:out value="${data.userId}" /></td>
						<td><c:out value="${data.firstName}" /></td>
						<td><c:out value="${data.lastName}" /></td>
						<td><c:out value="${data.dateOfBirth}" /></td>
						<td><c:out value="${data.emailId}" /></td>
						<td><c:out value="${data.gender}" /></td>
						<td><c:out value="${data.contactNo}" /></td>
						<td><c:out value="${data.languages}" /></td>
						<td><c:out value="${data.password}" /></td>
						<td>
							<form action="RegistrationServlet" method="post">
								<input type="hidden" name="userId" value="${data.userId}" />
								<button type="submit" name="btnValue" value="update1">
									Update</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<br> <br>
			</div>
			<br>
			<br>
			
			<div class="row">
			
			<table align="center" border=2>
				<tr>
					<th>Address Id</th>
					<th>Street 1</th>
					<th>Street 2</th>
					<th>Pincode</th>
					<th>City</th>
					<th>State</th>
					<th>Country</th>
				</tr>
				<c:forEach items="${addressData}" var="addressData">
					<tr>
						<td><c:out value="${addressData.addressId}" /></td>
						<td><c:out value="${addressData.street1}" /></td>
						<td><c:out value="${addressData.street2}" /></td>
						<td><c:out value="${addressData.pincode}" /></td>
						<td><c:out value="${addressData.city}" /></td>
						<td><c:out value="${addressData.state}" /></td>
						<td><c:out value="${addressData.country}" /></td>
					</tr>
				</c:forEach>
			</table>
			
			</div>
			<br> <br>
			<a href="<%=request.getContextPath()%>/home.jsp">Back</a>
			</div>
			
		</c:if>
		
	
</body>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap4.min.js"></script>
<script>

	$(document).ready(function() {
		$('#example').DataTable(

		{

			"aLengthMenu" : [ [ 5, 10, 25, -1 ], [ 5, 10, 25, "All" ] ],
			"iDisplayLength" : 5
		});
	});
</script>

</html>