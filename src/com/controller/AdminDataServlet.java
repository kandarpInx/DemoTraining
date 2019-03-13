package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.AddressModel;
import com.model.ImageModel;
import com.model.UserModel;
import com.service.AdminDataService;
import com.service.impl.AdminDataServiceImpl;


/**
 * Servlet implementation class AdminDataServlet
 */
@WebServlet("/AdminDataServlet")
public class AdminDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminDataService ad = new AdminDataServiceImpl();
		
		HttpSession session = request.getSession();
		
		if(session!=null && session.getAttribute("userDetails") != null) {
		
			@SuppressWarnings("unchecked")
			Map<String, String> userDetails = (Map<String, String>) session.getAttribute("userDetails");		
			UserModel um = new UserModel();
			
			if(userDetails.get("role").equals("admin")) {
				ArrayList<UserModel> data = ad.retriveData(um);
				request.setAttribute("data", data);
				request.getRequestDispatcher("/adminData.jsp").forward(request, response);		
			}
			if(userDetails.get("role").equals("user")) {
				
				String id = userDetails.get("userId");
				
				ArrayList<UserModel> data = ad.retriveUserData(id);
				List<AddressModel> addressData = ad.retriveAddressData(id);
				List<ImageModel> imageData;
				try {
					imageData = ad.retriveImageData(id);
					request.setAttribute("imageData", imageData);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				request.setAttribute("addressData", addressData);
				request.setAttribute("data", data);
				request.getRequestDispatcher("/adminData.jsp").forward(request, response);		
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminDataService ad = new AdminDataServiceImpl();
		String id = request.getParameter("userId");
		List<AddressModel> AddressData = ad.retriveAddressData(id);
		ObjectMapper objectMapper = new ObjectMapper();
		
		String json = objectMapper.writeValueAsString(AddressData);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    
	}
}
