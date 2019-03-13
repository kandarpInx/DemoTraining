<%@ include file="header.jsp" %>
<%
    session=request.getSession(false);
    if(session.getAttribute("userDetails")!=null)
    {
        response.sendRedirect("AdminDataServlet");
    }

%>

    <div class="container">	
      <form name="myform" class="form-horizontal" action="LoginServlet" method="post">
        <fieldset>

          <!-- Form Name -->
          <h1 align="center"> Login Page </h1>
			<br><br>

          <!-- Text input-->
          <div class="form-group">
            <label class="col-md-4 control-label">User Name</label>  
            <div class="col-md-4">
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                <input type="text" name="emailId" class="form-control" placeholder="abcd@def.com" required>
              </div>
            </div>
            <div class="col-md-4">
              <p id="error1"></p>
            </div>
          </div>

          <!-- password input-->
          <div class="form-group">
            <label class="col-md-4 control-label">Password</label>  
            <div class="col-md-4">
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                <input type="password" name="password" onselectstart="return false" onpaste="return false;" onCopy="return false" onCut="return false" class="form-control" placeholder="password" required>
              </div>
            </div>
            <div class="col-md-4">
              <p id="error2"></p>
            </div>
          </div>
		  
		  
		  <div class="form-group">
            <label class="col-md-4 control-label"></label>  
            <div class="col-md-4">
              <div class="input-group">
              <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%>
              <br>
              	New User?? <a href="RegistrationServlet"> Click Here </a>
			<br>
              	Forgot Password ??? <a href="forgot.jsp"> Click Here </a>
              </div>
            </div>
            <div class="col-md-4">
              <p id="error2"></p>
            </div>
          </div>

          <!-- Button -->
          <div class="form-group">
            <label class="col-md-4"></label>
            <div class="col-md-4">
              <button type="submit" id="submit" class="btn btn-warning">Submit </button>
              <button type="reset" class="btn btn-default" >Reset </button>
            </div>
          </div>

        </fieldset>
      </form>
    </div>
  <!-- /.container -->
<%@ include file="footer.jsp" %>