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
	<c:choose>
		<c:when test="${sessionScope.userDetails.userId ne null}">
			<title> Update Form </title>
		</c:when>
		<c:otherwise>
			<title> Registration Form </title>
		</c:otherwise>
	</c:choose>
	
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css">
  </head>
  <body>
  		<c:forEach items="${data}" var="data">
    <div class="container">
			<form name="myform" class="form-horizontal"
				action="RegistrationServlet" method="post">
				<fieldset>
					<c:choose>
						<c:when test="${sessionScope.userDetails.userId ne null}">


							<h1 align="center">Update Form</h1>
						</c:when>
						<c:otherwise>
							<h1 align="center">Registration Form</h1>
						</c:otherwise>
					</c:choose>



					<br>
					<br>
					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">First Name *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									name="firstName" value="${data.firstName}" id="firstname"
									class="form-control" placeholder="First Name"
									onblur="validateFname()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error"></p>
						</div>
					</div>

					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Last Name *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									name="lastName" value="${data.lastName}" id="lastname"
									class="form-control" placeholder="Last Name"
									onblur="validateLname()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error1"></p>
						</div>
					</div>

					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Birth Date *</label>
						<div class="col-md-4">
							<div class="input-group datepick">
								<input type="text" class="form-control"
									value="${data.dateOfBirth}" name="dateOfBirth" id="birthdate2">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error4"></p>
						</div>
					</div>

					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Phone No. *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input type="text"
									id="phoneno" name="contactNo" value="${data.contactNo}"
									class="form-control" value="" onblur="validatePhone()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error5"></p>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label">E-Mail *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input type="text"
									name="emailId" value="${data.emailId}" id="emailid"
									class="form-control" placeholder="abcd@def.com"
									onblur="validateEmail()"
									onkeypress="return preventSpace(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error6"></p>
						</div>
					</div>

					<!-- password input-->
					<div class="form-group">
						<label class="col-md-4 control-label">Password *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									type="password" name="password" id="password"
									value="${data.password}" onselectstart="return false"
									onpaste="return false;" onCopy="return false"
									onCut="return false" class="form-control"
									placeholder="password" rel="txtTooltip"
									title="Password must have 8-12 characters & atleast hve one capital one small one one spacial char and one number."
									onblur="validatePassword()" onkeyup="validatePassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error7"></p>
						</div>
					</div>

					<!-- Password input-->
					<div class="form-group">
						<label class="col-md-4 control-label">Confirm Password *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									type="password" value="${data.password}"
									onpaste="return false;" onCopy="return false"
									onCut="return false" id="confirmpassowrd" class="form-control"
									placeholder="confirm password" onblur="validateCpassword()"
									onkeyup="validateCpassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error8"></p>
						</div>
					</div>

					<!-- radio checks -->
					<div class="form-group">
						<c:set var="gender" value="${data.gender}"></c:set>
						<label class="col-md-4 control-label">Gender *</label>
						<div class="col-md-4">
							<div class="radio">
								<label> <input name="gender" type="radio" id="gender1"
									value="M" <c:if
test="${gender=='M'}">checked</c:if>
									onblur="validateGender()" /> Male
								</label>
							</div>
							<div class="radio">
								<label> <input name="gender" type="radio" id="gender2"
									value="F" <c:if
test="${gender=='F'}">checked</c:if>
									onblur="validateGender()" /> Female
								</label>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error9"></p>
						</div>
					</div>


					<!-- checkboxes-->

					<div class="form-group">
						<c:set var="language" value="${data.languages}"></c:set>
						<label class="col-md-4 control-label">Languages *</label>
						<div class="col-md-4">
							<div class="input-group">
								<div class="checkbox">
									<label><input name="languages" id="en" type="checkbox"
										value="english" onchange="validateLanguage()"
										<c:if test
="${fn:contains(language,'english')}">checked</c:if>>English</label>
								</div>
								<div class="checkbox">
									<label><input name="languages" id="hi" type="checkbox"
										value="hindi" onchange="validateLanguage()"
										<c:if test
="${fn:contains(language,'hindi')}">checked</c:if>>Hindi</label>
								</div>
								<div class="checkbox">
									<label><input id="gj" name="languages" type="checkbox"
										value="gujarati" onchange="validateLanguage()"
										<c:if test
="${fn:contains(language,'gujarati')}">checked</c:if>>Gujarati</label>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error15"></p>
						</div>
					</div>
					
					
					
					
					
					
					
					
		<c:forEach items="${addressData}" var="addressData">	
		
					<!-- Text input -->
					<div id="sections">
						<div class="section">
							<fieldset>
							
								<input type="hidden" name="addressId" value="${addressData.addressId}">
								<div class="form-group">
									<label class="col-md-4 control-label">Address *</label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"></span> <input type="text"
												name="street1" id="street1" class="form-control"
												placeholder="Street 1" value="${addressData.street1}" onblur="">
										</div>
									</div>
									<div class="col-md-4">
										<p></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label"></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"></span> <input type="text"
												name="street2" id="street2" class="form-control"
												placeholder="Street 2" value="${addressData.street2}" onblur="">
										</div>
									</div>
									<div class="col-md-4">
										<p id="error"></p>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label"></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"></span> <input type="text"
												name="city" id="city" class="form-control"
												placeholder="City" value="${addressData.city}" onblur="">
										</div>
									</div>
									<div class="col-md-4">
										<p id="error"></p>
									</div>
								</div>

								<!-- Text input -->

								<div class="form-group">
									<label class="col-md-4 control-label">Zip Code *</label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-home"></i></span> <input type="text"
												name="pincode" id="zipcode" class="form-control"
												placeholder="123456" value="${addressData.pincode}" onblur="validatePin()">
										</div>
									</div>
									<div class="col-md-4">
										<p id="error10"></p>
									</div>
								</div>

								<!-- Select Basic -->

								<div class="form-group">
									<label class="col-md-4 control-label">State</label>
									<c:set var="state" value="${addressData.state}"></c:set>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select name="state"
												id="state" class="form-control selectpicker">
												<option value="">Please select your state</option>
												<option value="gujarat" <c:if test
="${fn:contains(state,'gujarat')}">selected</c:if>>Gujarat</option>
												<option value="rajasthan" <c:if test
="${fn:contains(state,'rajasthan')}">selected</c:if>>Rajasthan</option>
											</select>
										</div>
									</div>
								</div>
								
								<!--   Select Basic -->

								<div class="form-group">
									<label class="col-md-4 control-label">Country</label>
									<c:set var="country" value="${addressData.country}"></c:set>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select name="country"
												id="country" class="form-control selectpicker">
												<option value="">Please select your country</option>
												<option value="india" <c:if test
="${fn:contains(country,'india')}">selected</c:if>>India</option>
												<option value="usa" <c:if test
="${fn:contains(country,'usa')}">selected</c:if>>USA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<p id="error10"></p>
									</div>
								</div>


								<div class="form-group">
									<div class="col-md-4"></div>
									<div class="col-md-4">
										<button class='remove btn btn-default'>Remove this address</button>
									</div>
								</div>
							</fieldset>
							
							
						</div>
					</div>

					</c:forEach>
					<div id="limit"></div>
					<div class="form-group">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<button class='addsection btn btn-default'>Add another
								Address</button>
						</div>
					</div>
	
	
	
	
	
	
	
	
	
	
	
					
					<c:choose>
						<c:when test="${sessionScope.userDetails.userId ne null}">
					<!-- Button -->
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">

									<input type="hidden" name="userId" value="${data.userId}" />
									<button type="submit" name="btnValue" value="update"
										id="submit" class="btn btn-warning">Update</button>
									<button type="submit" name="btnValue" value="retrieveData"
										class="btn btn-default">Cancel</button>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<div class="form-group">
								<label class="col-md-4 control-label"> </label>
								<div class="col-md-4">
									<div class="input-group">
										<div class="checkbox">
											<label><input id="condition" type="checkbox"
												onclick="validateCheck()" value="english">Accept all
												terms and conditions</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4"></div>
								<div class="col-md-4">
									<p>Fields with (*) are mendatory to fill</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">
									<button type="submit" name="btnValue" value="insert"
										id="submit" class="btn btn-warning">Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</fieldset>
			</form>
		</div>
  <!-- /.container -->

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/register.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script src="js/app.js"></script>
    <script language="javascript" src="https://momentjs.com/downloads/moment.js"></script>
	<script language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>


	</c:forEach>
  </body>
</html>