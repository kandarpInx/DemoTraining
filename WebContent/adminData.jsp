<%@ include file="header.jsp" %>
	<!---------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	----------------------------------------------Admin Table--------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------------------  -->

	<c:if test="${sessionScope.userDetails.role eq 'admin'}">
		
		<div class="container">
			<div class="row">
				<div class="head">
					<div class="col-md-4">
						<h1>  </h1>
					</div>
					<div class="col-md-4">
						<h1> All Data </h1>
					</div>
				</div>
			</div>
			<br>
			<br>
			<div class="row">
				<div class="col-md-12">
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
							<th>Address</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${data}" var="data">
							<tr>
								<td class="nr"><c:out value="${data.userId}" /></td>
								<td><c:out value="${data.firstName}" /></td>
								<td><c:out value="${data.lastName}" /></td>
								<td><c:out value="${data.dateOfBirth}" /></td>
								<td><c:out value="${data.emailId}" /></td>
								<td><c:out value="${data.gender}" /></td>
								<td><c:out value="${data.contactNo}" /></td>
								<td><c:out value="${data.languages}" /></td>
								<td>
								<button name="btnValue" id="modalButton" class="btn btn-light theButton" 
								data-toggle="modal" data-target=".bs-example-modal-lg"> address 
								</button> </td>
								<td>
									<button class="btn btn-link popovers" data-toggle="popover" title="" data-placement="bottom"
									data-content=
									'<form action="RegistrationServlet" method="post">
										<input type="hidden" name="userId" value="${data.userId}" /> 
										<button type="submit" class="btn btn-link" name="btnValue" value="update1"> 
											Update
										</button> 
									</form>
									<form action="RegistrationServlet" method="post">
										<input type="hidden" name="userId" value="${data.userId}" /> 
										<c:if test="${sessionScope.userDetails.userId ne data.userId}">
										<button type="submit" class="btn btn-link" 
										name="btnValue" value="delete" onclick="return confirm(&#39;Are you sure?&#39;);"> 
											Remove
										</button>
										</c:if> 
									</form>' >More Actions</button>
								</td>
							</tr>
						</c:forEach>
						</tbody>
						
				</table>
				</div>
				</div>
				
	</div>
				<br>
				<br>
				<br>
				<br>
				<br>
	</c:if>
	<!---------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	----------------------------------------------User Table---------------------------------------------
	-----------------------------------------------------------------------------------------------------
	-----------------------------------------------------------------------------------------------------
	------------------------------------------------------------------------------------------------  -->
	<c:if test="${sessionScope.userDetails.role eq 'user'}">
	
	<div class="container">
	
		<div class="row">
				<div class="head">
					<div class="col-md-4">
						<h1>  </h1>
					</div>
					<div class="col-md-4">
						<h1> ${sessionScope.userDetails.firstName}'s Data </h1>
					</div>
					<div class="col-md-4 logout">
					</div>
				</div>
			</div>
			<br>
			<br>			
			<div class="row">
			<div class="col-md-12">
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
						<th>image</th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${data}" var="data">
					<tr>
						<td ><c:out value="${data.userId}" /></td>
						<td><c:out value="${data.firstName}" /></td>
						<td><c:out value="${data.lastName}" /></td>
						<td><c:out value="${data.dateOfBirth}" /></td>
						<td><c:out value="${data.emailId}" /></td>
						<td><c:out value="${data.gender}" /></td>
						<td><c:out value="${data.contactNo}" /></td>
						<td><c:out value="${data.languages}" /></td>
						<c:forEach items="${imageData}" var="id">
						<td><img src="data:image/jpg;base64,${id.imageString}" width="100" height="100"/></td>
						</c:forEach>
						<td>
							<form action="RegistrationServlet" method="post">
								<input type="hidden" name="userId" value="${data.userId}" />
								<button type="submit" class="btn btn-regular" name="btnValue" value="update1">
									Update</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
			</div>
			<br>
			
			<div class="row">
			<div class="col-md-12">
			<h3> Addresses </h3>
			<table id="example" class="table table-striped table-bordered" style="width: 100%">
			<thead>
				<tr>
					<th>Address Id</th>
					<th>Street 1</th>
					<th>Street 2</th>
					<th>Pin code</th>
					<th>City</th>
					<th>State</th>
					<th>Country</th>
				</tr>
			</thead>
			<tbody>
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
			</tbody>
			</table>
			</div>
			</div>
			</div>
			<br><br><br><br>
		</c:if>
		<!-- Modal -->
		<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h3 class="modal-title">Addresses</h3>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
        			<table id="address" class="table table-striped table-bordered" style="width: 100%">
        			<thead>
						<tr>
							<th>Address Id</th>
							<th>Street 1</th>
							<th>Street 2</th>
							<th>Pincode</th>
							<th>City</th>
							<th>State</th>
							<th>Country</th>
						</tr>
					</thead>
					</table>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
<%@ include file="footer.jsp" %>
