package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.AdminDataService;
import com.service.impl.AdminDataServiceImpl;


/**
 * Servlet implementation class EmailAddressValidationServlet
 */
@WebServlet("/EmailAddressValidationServlet")
public class EmailAddressValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDataService ad = new AdminDataServiceImpl();
		ObjectMapper objectMapper = new ObjectMapper();
		String emailId = request.getParameter("emailid");
		int count = ad.isEmailExists(emailId);
		String json1 = objectMapper.writeValueAsString(count);
		response.getWriter().write(json1);
		
	}

}
