$(document).ready(function(){
	$('input[rel="txtTooltip"]').tooltip();
});

$(document).ready(function() {
  $('.datepick').datetimepicker({
    format: 'Y/MM/DD',
    maxDate: 'now',
    ignoreReadonly: true
  });
});

function validateFname()
{ 
	var fname = myform.firstname.value;
	var i;
	var temp = 0;
	var charCode = '';

	if(fname.length==0)
	{
 	   document.getElementById('error').innerHTML="Please insert first name";
 	   document.getElementById('firstname').style.borderColor = "red";
 	}
 	else if(fname.length<3)
 	{
 		document.getElementById('error').innerHTML="Name should have atleast 3 chars";
 		document.getElementById('firstname').style.borderColor = "red";
 	}
	else if (fname != "")
	{
		for (i=0;i<fname.length;i++)
		{
			charCode = fname.charCodeAt(i);
			if( (charCode >= 48 && charCode<=57) || (charCode >= 32 && charCode <= 47) || (charCode >= 58 && charCode <= 64) || (charCode >= 91 && charCode <= 96) || (charCode >= 123 && charCode <= 126) )
			{
				temp = 1;
			}
			if (temp==1)
			{
				document.getElementById('error').innerHTML="Only Alphabets are allowed ";
				document.getElementById('firstname').style.borderColor = "red";
			}
			else
			{
				document.getElementById('error').innerHTML=" ";
				document.getElementById('firstname').style.borderColor = "#ccc";
			}
		}
	}
	else
	{
		document.getElementById('error').innerHTML=" ";
		document.getElementById('firstname').style.borderColor = "#ccc";
	}
}

function validateLname()
{
	validateFname();
	var lname = myform.lastname.value;
	var i;
	var temp = 0;
	var charCode = '';

	if(lname.length==0)
	{
 	   document.getElementById('error1').innerHTML="Please insert Last name";
 	   document.getElementById('lastname').style.borderColor = "red";
 	}
 	else if(lname.length<3)
 	{
 		document.getElementById('error1').innerHTML="Last name should have atleast 3 chars";
 		document.getElementById('lastname').style.borderColor = "red";
 	}
	else if (lname != "")
	{
		for (i=0;i<lname.length;i++)
		{
			charCode = lname.charCodeAt(i);
			if((charCode>=48&&charCode<=57) || (charCode>=32 && charCode<=47) || (charCode>=58 && charCode<=64) || (charCode>=91 && charCode<=96) || (charCode>=123 && charCode<=126))
			{
				temp = 1;
			}
			if (temp==1)
			{
				document.getElementById('error1').innerHTML="Only Alphabets are allowed ";
				document.getElementById('lastname').style.borderColor = "red";
			}
			else
			{
				document.getElementById('error1').innerHTML=" ";
				document.getElementById('lastname').style.borderColor = "#ccc";
			}
		}
	}
	else
	{
		document.getElementById('error1').innerHTML=" ";
		document.getElementById('lastname').style.borderColor = "#ccc";
	}
}

function validatePhone(){

	validateFname();
	validateLname();
	
	var phone = myform.phoneno.value;
	var regex = /^\+?(91)\)?([0-9]{10})$/;

	if(phone.length==3)
	{
	   document.getElementById('error5').innerHTML="Please insert phone number";
	   document.getElementById('phoneno').style.borderColor = "red";
	}
	else if(phone == "+91 0000000000")
	{
		document.getElementById('error5').innerHTML="All zeros are not allowed";
		document.getElementById('phoneno').style.borderColor = "red";
	}
	else if(regex.test(phone))
	{
		document.getElementById('error5').innerHTML=" ";
		document.getElementById('phoneno').style.borderColor = "#ccc";
	}
	else
	{
		document.getElementById('error5').innerHTML="Invalid number Or Format";
		document.getElementById('phoneno').style.borderColor = "red";
	}	
}
$('#phoneno').keyup(function(){

    if(this.value.indexOf('+91') !== 0 ){ 
        this.value='+91 ' + this.value;
    }
});


function validateEmail(){

	validateFname();
	validateLname();
	validatePhone();
	
	var email = myform.emailid.value;

	if(email.length<1){
		document.getElementById('error6').innerHTML="Please enter email address";
	   	document.getElementById('emailid').style.borderColor = "red";
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
					document.getElementById('error6').innerHTML="Invalid email";
					document.getElementById('emailid').style.borderColor = "red";
					break;
				}
			}
			var wordsWithDot = postAt.split(".");
			if(wordsWithDot.length === 2 || wordsWithDot.length === 3){
				if(wordsWithDot[wordsWithDot.length-1].length < 2 || wordsWithDot[wordsWithDot.length-2].length < 3) {
					document.getElementById('error6').innerHTML="Invalid email address";
					document.getElementById('emailid').style.borderColor = "red";
				} else {
					document.getElementById('error6').innerHTML="";
					document.getElementById('emailid').style.borderColor = "#ccc";
				}

			} else {
				document.getElementById('error6').innerHTML="Invalid email address";
				document.getElementById('emailid').style.borderColor = "red";
			}
		} else {
			document.getElementById('error6').innerHTML="Invalid email address";
			document.getElementById('emailid').style.borderColor = "red";
		}
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

function validatePassword()
{

	validateFname();
	validateLname();
	validatePhone();
	validateEmail();

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
 	   document.getElementById('error7').innerHTML="Please insert password";
 	   document.getElementById('password').style.borderColor = "red";
 	}
    else if (pwd.length<8 || pwd.length>12)
     {
       document.getElementById('error7').innerHTML="password between 8 to 12";
       document.getElementById('password').style.borderColor = "red";
     }
     else if (hasCapital==false)
     {
        document.getElementById('error7').innerHTML="Capital latter is missing";
        document.getElementById('password').style.borderColor = "red";
     }
     else if (hasNumeric==false)
     {
        document.getElementById('error7').innerHTML="Number is missing";
        document.getElementById('password').style.borderColor = "red";
     }
     else if (hasSpecial==false)
     {
       document.getElementById('error7').innerHTML="Spacial character is missing";
       document.getElementById('password').style.borderColor = "red";
     }
     else
     {
        document.getElementById("error7").innerHTML=" ";
        document.getElementById('password').style.borderColor = "#ccc";
     }
}
function validateCpassword()
{

	validateFname();
	validateLname();
	validatePhone();
	validateEmail();
	validatePassword();

   var cpwd = myform.confirmpassowrd.value;
   var pwd = myform.password.value;

   if(cpwd.length==0)
	{
 	   document.getElementById('error8').innerHTML="Please insert confirm password";
 	   document.getElementById('confirmpassowrd').style.borderColor = "red";
 	}

   else if (cpwd != pwd)
   	{
   		document.getElementById('error8').innerHTML="Not match";
   		document.getElementById('confirmpassowrd').style.borderColor = "red";
   	}
   	else
   	{
   		document.getElementById('error8').innerHTML=" ";
   		document.getElementById('confirmpassowrd').style.borderColor = "#ccc";
   	}
}

function validatePin()
{

	validateFname();
	validateLname();
	validatePhone();
	validateEmail();
	validatePassword();
	validateCpassword();

	var a = myform.zipcode.value;

	if(a.length==0)
	{
	   document.getElementById('error10').innerHTML="Please insert pin code";
	   document.getElementById('zipcode').style.borderColor = "red";
	}
	else if(isNaN(a) == true)
	{
		document.getElementById('error10').innerHTML="Please insert numerics only";
		document.getElementById('zipcode').style.borderColor = "red";
	}
	else if(a.length!==6)
	{
		document.getElementById('error10').innerHTML="Zip code must have 6 digits";	
		document.getElementById('zipcode').style.borderColor = "red";
	}
	else if(a<1)
	{
		document.getElementById('error10').innerHTML="Invalid zip code";
		document.getElementById('zipcode').style.borderColor = "red";
	}
	else
	{
		document.getElementById('error10').innerHTML=" ";
		document.getElementById('zipcode').style.borderColor = "#ccc";
	}
}

function validateCheck() 
{
	validateFname();
	validateLname();
	validatePhone();
	validateEmail();
	validatePassword();
	validateCpassword();
	validatePin();
	validateLanguage();
	validateGender();
	
    var male = document.getElementById('gender1');
    var female = document.getElementById('gender2');
    var english = document.getElementById('en');
    var hindi = document.getElementById('hi');
    var guj = document.getElementById('gj');
    var agree = document.getElementById('condition');
    var btn = document.getElementById('submit');
    if(english.checked == false && hindi.checked == false && guj.checked == false 
        && male.checked == false && female.checked == false && agree.checked == false){
            btn.disabled = true;
    }

    else if(( english.checked == true || hindi.checked == true || guj.checked == true )
        && (male.checked == true || female.checked == true) && agree.checked == true){
        btn.disabled = false;
    }

    else if(( english.checked == false || hindi.checked == false || guj.checked == false )
        || (male.checked == false || female.checked == false) || agree.checked == false){
        btn.disabled = true;
    }
} 
/*function validateCheck(){

	var term = document.getElementById('condition');
	var btn= document.getElementById('submit');
	if(term.checked == true)
	{
		btn.disabled=false;
	}
	else{
		btn.disabled=true;
	}
}*/
function validateGender()
{

	validateFname();
	validateLname();
	validatePhone();
	validateEmail();
	validatePassword();
	validateCpassword();
	validatePin();
	
	var c1 = document.getElementById('gender1');
	var c2 = document.getElementById('gender2');
	
	if(c1.checked || c2.checked)
	{
		document.getElementById('error9').innerHTML="";
	}
	else
	{
		document.getElementById('error9').innerHTML="Please select Gender";
	}
}
function validateLanguage()
{

	validateFname();
	validateLname();
	validatePhone();
	validateEmail();
	validatePassword();
	validateCpassword();
	validatePin();
	
	var c1 = document.getElementById('en');
	var c2 = document.getElementById('hi');
	var c3 = document.getElementById('gj');
	if(c1.checked || c2.checked || c3.checked)
	{
		document.getElementById('error15').innerHTML="";
	}
	else
	{
		document.getElementById('error15').innerHTML="Check atleast one language";
	}
}
