package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter({"/LoginServlet","/RegistrationServlet","/AdminDataServlet"})
public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		
		assert res instanceof HttpServletResponse : res.getClass();
		HttpServletResponse response = (HttpServletResponse) res;
		assert req instanceof HttpServletRequest : req.getClass();
		HttpServletRequest request =  (HttpServletRequest) req;
		
		HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/LoginServlet";
        String registrationURI = request.getContextPath() + "/RegistrationServlet";

        boolean loggedIn = session != null && session.getAttribute("userDetails") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean registrationRequest = request.getRequestURI().equals(registrationURI);

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
		if (loggedIn || loginRequest || registrationRequest) {

			String button = request.getParameter("btnValue");
			if (registrationRequest && !loggedIn && button != null
					&& !(button.equals("insert") || button.equals("forgot"))) {

				/*
				 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
				 * out.println("<script type=\"text/javascript\">");
				 * out.println("alert('Please Login again...!');"); out.println("</script>");
				 */

				request.setAttribute("error", "Please Login again");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.include(request, response);
			} 
			else {
				chain.doFilter(request, response);
			}
		} 
        else {
			/*
			 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
			 * out.println("<script type=\"text/javascript\">");
			 * out.println("alert('Please Login again...!');"); out.println("</script>");
			 */
        	request.setAttribute("error", "Please Login again");
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            rd.include(request, response);
        }
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
