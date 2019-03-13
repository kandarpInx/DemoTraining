$(document).ready(function(){
	$('input[rel="txtTooltip"]').tooltip({});
});

$(document).ready(function() {
  $('.datepick').datetimepicker({
    format: 'Y/MM/DD',
    maxDate: 'now',
    ignoreReadonly: true
  });
});

function preventNumerics(e){
	var k = window.event.keyCode;
	 if (k < 48 || k > 57){
        return true;
     }else{
    	 return false;
     }
}
function preventAlphabets(e){
	var k = window.event.keyCode;
	 if (k < 48 || k > 57){
        return false;
     }else{
    	 return true;
     }
}
function preventSpace(e){
	var k = window.event.keyCode;
	if((k>64 && k<91) || (k>96 && k<123) || (k>47 && k <58) || k == 8 || k == 9 
		|| k==64 || k==95 || k==46)
	{ 
		return true;
	}
	else{
		return false;
	}
}
function maxLengthCheck(object) {
    if (object.value.length > object.maxLength)
      object.value = object.value.slice(0, object.maxLength)
}




function validateFname()
{ 
	var fname = myform.firstname.value;

	if(fname.length==0)
	{
 	   document.getElementById('error').innerHTML="Please insert first name";
 	   document.getElementById('firstname').style.borderColor = "red";
 	   return false;
 	}
 	else if(fname.length<3)
 	{
 		document.getElementById('error').innerHTML="Name should have atleast 3 chars";
 		document.getElementById('firstname').style.borderColor = "red";
 		return false;
 	}
	else
	{
		document.getElementById('error').innerHTML=" ";
		document.getElementById('firstname').style.borderColor = "#ccc";
		return true;
	}
}

function validateLname()
{
	var lname = myform.lastname.value;

	if(lname.length==0)
	{
 	   document.getElementById('error1').innerHTML="Please insert Last name";
 	   document.getElementById('lastname').style.borderColor = "red";
 	   return false;
 	}
 	else if(lname.length<3)
 	{
 		document.getElementById('error1').innerHTML="Last name should have atleast 3 chars";
 		document.getElementById('lastname').style.borderColor = "red";
 		return false;
 	}
	else
	{
		document.getElementById('error1').innerHTML=" ";
		document.getElementById('lastname').style.borderColor = "#ccc";
		return true;
	}
}

function validateBirthdate(){
	var birthdate = $("#birthdate").val(); 
	if(birthdate.length==0)
	{
 	   document.getElementById('error2').innerHTML="Please insert birthdate";
 	   document.getElementById('birthdate').style.borderColor = "red";
 	   return false;
 	}
	else{
		document.getElementById('error2').innerHTML=" ";
		document.getElementById('birthdate').style.borderColor = "#ccc";
		return true;
	}
}

function validatePhone() {
	var phone = myform.phoneno.value;
	var regex = /^\+?(91)\)?([0-9]{10})$/;

	if(phone.length==3)
	{
	   document.getElementById('error3').innerHTML="Please insert phone number";
	   document.getElementById('phoneno').style.borderColor = "red";
	   return false;
	}
	else if(phone == "+910000000000")
	{
		document.getElementById('error3').innerHTML="All zeros are not allowed";
		document.getElementById('phoneno').style.borderColor = "red";
		return false;
	}
	else if(regex.test(phone))
	{
		document.getElementById('error3').innerHTML=" ";
		document.getElementById('phoneno').style.borderColor = "#ccc";
		return true;
	}
	else
	{
		document.getElementById('error3').innerHTML="Invalid number Or Format";
		document.getElementById('phoneno').style.borderColor = "red";
		return false;
	}	
}
$('#phoneno').keyup(function(){

    if(this.value.indexOf('+91') !== 0 ){ 
        this.value='+91' + this.value;
    }
});

function validateEmail() {
	var email = myform.emailid.value;

	if(email.length<1){
		document.getElementById('error4').innerHTML="Please enter email address";
	   	document.getElementById('emailid').style.borderColor = "red";
	   	return false;
	}
	else{
		var splittedEmail = email.split("@");
		if(splittedEmail.length === 2) {
			var postAt = splittedEmail[1];
			var preAt = splittedEmail[0];
			for (var i=0;i<preAt.length;i++)
			{
				var temp=0;
				charCode = preAt.charCodeAt(i);
				if(!(charCode>=32 && charCode<=44) || (charCode>=58 && charCode<=63) || (charCode>=91 && charCode<=94) || (charCode>=123 && charCode<=126))
				{
					temp = 1;
					document.getElementById('error4').innerHTML="Invalid email";
					document.getElementById('emailid').style.borderColor = "red";
					break;
				}
			}
			var wordsWithDot = postAt.split(".");
			if(wordsWithDot.length === 2 || wordsWithDot.length === 3){
				if(wordsWithDot[wordsWithDot.length-1].length < 2 || wordsWithDot[wordsWithDot.length-2].length < 3) {
					document.getElementById('error4').innerHTML="Invalid email address";
					document.getElementById('emailid').style.borderColor = "red";
				} else {
					document.getElementById('error4').innerHTML="";
					document.getElementById('emailid').style.borderColor = "#ccc";
					return true;
				}

			} else {
				document.getElementById('error4').innerHTML="Invalid email address";
				document.getElementById('emailid').style.borderColor = "red";
			}
		} else {
			document.getElementById('error4').innerHTML="Invalid email address";
			document.getElementById('emailid').style.borderColor = "red";
		}
		return false;
	}
}

function checkemail() {
	var status = true;
	$.ajax({
		type : "post",
		url : "EmailAddressValidationServlet",
		data : {
			emailid : document.getElementById('emailid').value
		},
		success : function(result) {
			if (result == 1) {
				document.getElementById('error4').innerHTML = "Email address already exists...";
				document.getElementById('emailid').style.borderColor = "red";
				status = false;
			}
		}
	});
	return status;
}

function validatePassword()
{
	alert(checkemail())
	var pwd = myform.password.value;
	var hasCapital = false;
	var hasSmall = false;
	var hasNumeric = false;
	var hasSpecial = false;
	var temp,i;

	for (i=0; i<pwd.length; i++)
	{
       temp = pwd.charCodeAt(i);
       
       if(temp>47 && temp<58)
       {
         hasNumeric = true;
       }
       else if(temp>64 && temp<91)
       {
        hasCapital = true;
       }
       else if(temp>96 && temp<123)
       {
          hasSmall = true;
       }
       else if((temp>32 && temp<48) || (temp>57 && temp<65) || (temp>90 && temp<97) ||(temp>122 && temp<127))
      {
        hasSpecial = true;
      }
    }
	if(pwd.length==0)
	{
 	   document.getElementById('error5').innerHTML="Please insert password";
 	   document.getElementById('password').style.borderColor = "red";
 	   return false;
 	}
    else if (pwd.length<8 || pwd.length>12)
     {
       document.getElementById('error5').innerHTML="password between 8 to 12";
       document.getElementById('password').style.borderColor = "red";
       return false;
     }
     else if (hasCapital==false)
     {
        document.getElementById('error5').innerHTML="Capital latter is missing";
        document.getElementById('password').style.borderColor = "red";
        return false;
     }
     else if (hasNumeric==false)
     {
        document.getElementById('error5').innerHTML="Number is missing";
        document.getElementById('password').style.borderColor = "red";
        return false;
     }
     else if (hasSpecial==false)
     {
       document.getElementById('error5').innerHTML="Spacial character is missing";
       document.getElementById('password').style.borderColor = "red";
       return false;
     }
     else
     {
        document.getElementById("error5").innerHTML=" ";
        document.getElementById('password').style.borderColor = "#ccc";
        return true;
     }
}
function validateCpassword()
{
   var cpwd = myform.confirmpassowrd.value;
   var pwd = myform.password.value;

   if(cpwd.length==0)
	{
 	   document.getElementById('error6').innerHTML="Please insert confirm password";
 	   document.getElementById('confirmpassowrd').style.borderColor = "red";
 	   return false;
 	}

   else if (cpwd != pwd)
   	{
   		document.getElementById('error6').innerHTML="Not match";
   		document.getElementById('confirmpassowrd').style.borderColor = "red";
   		return false;
   	}
   	else
   	{
   		document.getElementById('error6').innerHTML=" ";
   		document.getElementById('confirmpassowrd').style.borderColor = "#ccc";
   		return true;
   	}
}

function validateGender()
{
	var c1 = document.getElementById('gender1');
	var c2 = document.getElementById('gender2');
	
	if(c1.checked || c2.checked)
	{
		document.getElementById('error7').innerHTML="";
		return true;
	}
	else
	{
		document.getElementById('error7').innerHTML="Please select Gender";
		return false;
	}
}

function validateLanguage()
{	
	var c1 = document.getElementById('en');
	var c2 = document.getElementById('hi');
	var c3 = document.getElementById('gj');
	if(c1.checked || c2.checked || c3.checked)
	{
		document.getElementById('error8').innerHTML="";
		return true;
	}
	else
	{
		document.getElementById('error8').innerHTML="Check atleast one language";
		return false;
	}
}
function validateImage(){
	var img = jQuery('#file').val();
	if(!img){
		 document.getElementById('error9').innerHTML="Please insert image";
	 	 document.getElementById('file').style.borderColor = "red";
	 	 return false;
	}
	else{
		document.getElementById('error9').innerHTML="";
	 	 document.getElementById('file').style.borderColor = "#ccc";
	 	 return true;
	}
}

function validateCheckbox(){
	validateFname();
	validateLname();
	validateBirthdate();
	validatePhone();
	validateEmail();
	validatePassword();
	validateCpassword();
	validateGender();
	validateLanguage();
	validateStreet1();
	validateStreet2();
	validateCity();
	validatePincode();
	validateState();
	validateCountry();
	validateImage();
	
	var condition = document.getElementById('condition');
    var btn = document.getElementById('submit');
   
    if((validateFname() && validateLname() && validateBirthdate() && validatePhone() && validateEmail() && validatePassword() && 
    		validateCpassword() && validateGender() && validateLanguage() && validateStreet1() && validateStreet2() && 
    		validateCity() && validatePincode() && validateState() && validateCountry() && validateImage()) 
    		&& condition.checked == true)
    {
        btn.disabled = false;
    }else{
        btn.disabled = true;
    }
}
function validateForm(event){
	
    if(validateFname() && validateLname() && validateBirthdate() && validatePhone() && validateEmail() &&
    		validateGender() && validateLanguage() && validateStreet1() && validateStreet2() && validateCity() 
    		&& validatePincode() && validateState() && validateCountry() && validateImage() && checkemail())
    {
    	alert("Successful");
    }
    else{
    	alert("Please fill required fields first...!");
    	event.preventDefault();
    }
}





function validateStreet1() {
	var status = true;
	$('.street1Class').each(function(index, obj){
		var street1 = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!street1) {
				$('#error_street1_' + idToAppend).html("Please enter street 1");
				$('#street1_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_street1_' + idToAppend).html("");
				$('#street1_'+idToAppend).css({"border-color":"#ccc"});
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!street1) {
				$('#error_street1_' + idToAppend).html("Please enter street 1");
				$('#street1_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_street1_' + idToAppend).html("");
				$('#street1_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}
function validateStreet2() {
	var status = true;
	$('.street2Class').each(function(index, obj){
		var street2 = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!street2) {
				$('#error_street2_' + idToAppend).html("Please enter street 2");
				$('#street2_'+idToAppend).css({"border-color":"red"});
				status = false;
				
			} else {
				$('#error_street2_' + idToAppend).html("");
				$('#street2_'+idToAppend).css({"border-color":"#ccc"});
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!street2) {
				$('#error_street2_' + idToAppend).html("Please enter street 2");
				$('#street2_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_street2_' + idToAppend).html("");
				$('#street2_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}
function validateCity() {
	var status = true;
	$('.cityClass').each(function(index, obj){
		var city = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!city) {
				$('#error_city_' + idToAppend).html("Please enter city");
				$('#city_'+idToAppend).css({"border-color":"red"});
				status = false;
				
			} else {
				$('#error_city_' + idToAppend).html("");
				$('#city_'+idToAppend).css({"border-color":"#ccc"});
				
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!city) {
				$('#error_city_' + idToAppend).html("Please enter city");
				$('#city_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_city_' + idToAppend).html("");
				$('#city_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}
function validatePincode() {
	var status = true;
	$('.pincodeClass').each(function(index, obj){
		var pincode = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!pincode) {
				$('#error_pincode_' + idToAppend).html("Please enter Pincode");
				$('#pincode_'+idToAppend).css({"border-color":"red"});
				status = false;
			} 
			else if(pincode.length!==6){
				$('#error_pincode_' + idToAppend).html("Invalid Pincode");
				$('#pincode_'+idToAppend).css({"border-color":"red"});
				status = false;
			}else {
				$('#error_pincode_' + idToAppend).html("");
				$('#pincode_'+idToAppend).css({"border-color":"#ccc"});
				
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!pincode) {
				$('#error_pincode_' + idToAppend).html("Please enter Pincode");
				$('#pincode_'+idToAppend).css({"border-color":"red"});
				status = false;
			}
			else if(pincode.length!==6){
				$('#error_pincode_' + idToAppend).html("Invalid Pincode");
				$('#pincode_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_pincode_' + idToAppend).html("");
				$('#pincode_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}
function validateState() {
	var status = true;
	$('.stateClass').each(function(index, obj){
		var state = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!state) {
				$('#error_state_' + idToAppend).html("Please select state");
				$('#state_'+idToAppend).css({"border-color":"red"});
				status = false;
				
			} else {
				$('#error_state_' + idToAppend).html("");
				$('#state_'+idToAppend).css({"border-color":"#ccc"});
				
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!state) {
				$('#error_state_' + idToAppend).html("Please select state");
				$('#state_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_state_' + idToAppend).html("");
				$('#state_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}
function validateCountry() {
	var status = true;
	$('.countryClass').each(function(index, obj){
		var country = obj.value;
		var splittedId = '';
		if(this.id && (this.id.match(/_/g) || []).length === 1) {
			var idToAppend = this.id.substring(this.id.lastIndexOf("_") + 1, this.id.length);
			if(!country) {
				$('#error_country_' + idToAppend).html("Please select country");
				$('#country_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_country_' + idToAppend).html("");
				$('#country_'+idToAppend).css({"border-color":"#ccc"});
			}
		} else if (this.id && (this.id.match(/_/g) || []).length === 2) {
			var idToAppend = this.id.substring(this.id.indexOf("_") + 1, this.id.length);
			if(!country) {
				$('#error_country_' + idToAppend).html("Please select country");
				$('#country_'+idToAppend).css({"border-color":"red"});
				status = false;
			} else {
				$('#error_country_' + idToAppend).html("");
				$('#country_'+idToAppend).css({"border-color":"#ccc"});
			}
		}
	});
	return status;
}