package com.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.UserModel;
import com.service.LoginService;
import com.service.impl.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String emailId = request.getParameter("emailId");
		String password = request.getParameter("password");
		
		UserModel um = new UserModel();
		
		um.setEmailId(emailId);
		um.setPassword(password);
		
		LoginService ls = new LoginServiceImpl();
		Map<String, String> userDetails = ls.getUserDetails(um);
		
		
		
		if(userDetails.get("role").equals("admin") || userDetails.get("role").equals("user"))
		{
			
			HttpSession session = request.getSession(); // creation
			session.setAttribute("userDetails", userDetails);
			session.setMaxInactiveInterval(5*60); // timeout
			response.sendRedirect(request.getContextPath() + "/AdminDataServlet");
		}
		else {
			String a = (String) request.getAttribute("error");
			System.out.println(a);
			request.setAttribute("error", a);
			request.setAttribute("error", userDetails.get("role")); 
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}
}