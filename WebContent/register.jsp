  <%@ include file="header.jsp" %>
  		<c:forEach items="${data}" var="data">
    <div class="container">

		<div class="row">
			<div class="head">
				<div class="col-md-4">
					<h1></h1>
				</div>
				<div class="col-md-4">
					<c:choose>
						<c:when test="${sessionScope.userDetails.userId ne null}">
							<h1 align="center">${data.firstName}'s Details</h1>
						</c:when>
						<c:otherwise>
							<h1 align="center">Registration Form</h1>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-4 logout">
	
				</div>
			</div>
		</div>



					
			<form name="myform" enctype='multipart/form-data' class="form-horizontal" 
			 action="RegistrationServlet" method="post">
				<fieldset>
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
									onblur="validateFname()"
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									onkeypress="return preventNumerics(event)">
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
									onblur="validateLname()"
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									onkeypress="return preventNumerics(event)">
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
									value="${data.dateOfBirth}" name="dateOfBirth" id="birthdate" onblur="validateBirthdate()">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error2"></p>
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
									class="form-control" value="" onblur="validatePhone()"
									oninput="maxLengthCheck(this)"
									maxlength = "13"
									onkeypress="return preventAlphabets(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error3"></p>
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
									onblur="validateEmail(),checkemail()"
									oninput="maxLengthCheck(this)"
									maxlength = "255"
									onkeypress="return preventSpace(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error4"></p>
						</div>
					</div>

<c:if test="${sessionScope.userDetails.userId eq null}">
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
									placeholder="password" data-placement="right"
									oninput="maxLengthCheck(this)"
									maxlength = "45" rel="txtTooltip"
									title="Password must have 8-12 characters & atleast hve one capital one small one one spacial char and one number."
									onblur="validatePassword()" onkeyup="validatePassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error5"></p>
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
									onCut="return false" 
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									id="confirmpassowrd" class="form-control"
									placeholder="confirm password" onblur="validateCpassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error6"></p>
						</div>
					</div>
</c:if>

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
							<p id="error7"></p>
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
										value="english" onblur="validateLanguage()"
										<c:if test
="${fn:contains(language,'english')}">checked</c:if>>English</label>
								</div>
								<div class="checkbox">
									<label><input name="languages" id="hi" type="checkbox"
										value="hindi" onblur="validateLanguage()"
										<c:if test
="${fn:contains(language,'hindi')}">checked</c:if>>Hindi</label>
								</div>
								<div class="checkbox">
									<label><input id="gj" name="languages" type="checkbox"
										value="gujarati" onblur="validateLanguage()"
										<c:if test
="${fn:contains(language,'gujarati')}">checked</c:if>>Gujarati</label>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error8"></p>
						</div>
					</div>
					
					
					
					
					
					
		<c:set var = "string1" value = "${addressData}"/>
		 <c:set var = "sectionsLength" value = "${fn:length(string1)}"></c:set>



					
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
												name="street1" id="street1_${addressData.addressId}" 
												class="form-control street1Class"
												placeholder="Street 1" value="${addressData.street1}"
												oninput="maxLengthCheck(this)"
												maxlength = "45"
												onblur="validateStreet1()">
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_street1" id="error_street1_${addressData.addressId}"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label"></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"></span> <input type="text"
												name="street2" id="street2_${addressData.addressId}" 
												class="form-control street2Class"
												placeholder="Street 2" value="${addressData.street2}" 
												oninput="maxLengthCheck(this)"
												maxlength = "45"
												onblur="validateStreet2()">
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_street2" id="error_street2_${addressData.addressId}"></p>
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-4 control-label"></label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"></span> <input type="text"
												name="city" id="city_${addressData.addressId}" 
												class="form-control cityClass"
												placeholder="City" value="${addressData.city}" 
												oninput="maxLengthCheck(this)"
												maxlength = "45"
												onblur="validateCity()"
												onkeypress="return preventNumerics(event)">
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_city" id="error_city_${addressData.addressId}"></p>
									</div>
								</div>

								<!-- Text input -->

								<div class="form-group">
									<label class="col-md-4 control-label">Pin Code *</label>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-home"></i></span> <input type="text"
												name="pincode" id="pincode_${addressData.addressId}" 
												class="form-control pincodeClass"
												value="${addressData.pincode}"
												placeholder="123456"
												oninput="maxLengthCheck(this)"
												maxlength = "6"
												onblur="validatePincode()"
												onkeypress="return preventAlphabets(event)">
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_pincode" id="error_pincode_${addressData.addressId}"></p>
									</div>
								</div>

								<!-- Select Basic -->

								<div class="form-group">
									<label class="col-md-4 control-label">State *</label>
									<c:set var="state" value="${addressData.state}"></c:set>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select name="state"
												id="state_${addressData.addressId}" class="form-control selectpicker stateClass"
												onblur="validateState()">
												<option value="">Please select your state</option>
												<option value="gujarat" <c:if test
="${fn:contains(state,'gujarat')}">selected</c:if>>Gujarat</option>
												<option value="rajasthan" <c:if test
="${fn:contains(state,'rajasthan')}">selected</c:if>>Rajasthan</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_state" id="error_state_${addressData.addressId}"></p>
									</div>
								</div>
								
								<!--   Select Basic -->

								<div class="form-group">
									<label class="col-md-4 control-label">Country *</label>
									<c:set var="country" value="${addressData.country}"></c:set>
									<div class="col-md-4">
										<div class="input-group">
											<span class="input-group-addon"><i
												class="glyphicon glyphicon-list"></i></span> <select name="country"
												id="country_${addressData.addressId}" 
												class="form-control selectpicker countryClass"
												onblur="validateCountry()">
												<option value="">Please select your country</option>
												<option value="india" <c:if test
="${fn:contains(country,'india')}">selected</c:if>>India</option>
												<option value="usa" <c:if test
="${fn:contains(country,'usa')}">selected</c:if>>USA</option>
											</select>
										</div>
									</div>
									<div class="col-md-4">
										<p name="error_country" id="error_country_${addressData.addressId}"></p>
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



<c:forEach items="${imageData}" var="id">
		<input type="hidden" name="imageId" value="${id.imageId}">
					<div class="form-group">
						<label class="col-md-4 control-label">Upload Image *</label>

						<div class="col-md-4">
							<div class="input-group">
								<input type="file" name="image" id="file" onblur="validateImage(),validateSubmit()">
								<output id="result"><c:if test="${sessionScope.userDetails.userId ne null}">
								<img class="thumbnail" src="data:image/jpg;base64,${id.imageString}"/>
								</c:if>
								</output>
							</div>
						</div>
						<div class="col-md-4">
							<small id="fileValidate" class="upload"></small>
							<p id="error9"></p>
						</div>
					</div>
					</c:forEach>










					<c:choose>
						<c:when test="${sessionScope.userDetails.userId ne null}">
					<!-- Button -->
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">

									<input type="hidden" name="userId" value="${data.userId}" />
									
									<button type="submit" name="btnValue" onclick="return validateForm(event);" value="update"
										id="submit" class="btn btn-primary">Update</button>
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
												onclick="validateCheckbox()">Accept all
												terms and conditions</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4"></div>
								<div class="col-md-4">
									<p>Fields with (*) are mandatory to fill</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">
									<button type="submit" name="btnValue" value="insert"
										id="submit" onclick="return validateForm(event);" 
										class="btn btn-primary" disabled>Submit</button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</div>
						</c:otherwise>
					</c:choose>
				</fieldset>
			</form>
		</div>
  <!-- /.container -->
</c:forEach>
<br>
<br>
<br>

<script type="text/javascript">
    var str = ${sectionsLength};
</script>
<%@ include file="footer.jsp" %>

