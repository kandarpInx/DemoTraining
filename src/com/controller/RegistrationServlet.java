package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.UserModel;
import com.service.AdminDataService;
import com.service.RegisterationService;
import com.service.impl.AdminDataServiceImpl;
import com.service.impl.RegstrationServiceImpl;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session!=null) {
		
			String button = request.getParameter("btnValue");
			
			AdminDataService ad = new AdminDataServiceImpl();
			
			if(button.equals("retrieveData")) {
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
					
					request.setAttribute("addressData", addressData);
					request.setAttribute("data", data);
					request.getRequestDispatcher("/adminData.jsp").forward(request, response);		
				}
			}
			
			if(button.equals("delete")) {
				String id = request.getParameter("userId");
				ad.deleteData(id);
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
			
			if(button.equals("update1")) {
				String id = request.getParameter("userId");
				
				ArrayList<UserModel> data = ad.retriveUserData(id);
				List<AddressModel> addressData = ad.retriveAddressData(id);
				
				request.setAttribute("addressData", addressData);
				request.setAttribute("data", data);
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			
			if(button.equals("update")) {
				
				RegisterationService rs = new RegstrationServiceImpl();
				
				String userId = request.getParameter("userId");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String dateOfBirth = request.getParameter("dateOfBirth");
				String emailId = request.getParameter("emailId");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				String contactNo = request.getParameter("contactNo");
				String languages[] = request.getParameterValues("languages");
				
				UserModel um = new UserModel();
				
				AddressModelList aml = new AddressModelList();
				List<AddressModel> beforeAddressData = ad.retriveAddressData(userId);
				List<AddressModel> afterAddressData = new ArrayList<AddressModel>();
				Set<String> insertedIds = new HashSet<String>();
				Set<String> deletedIds = new HashSet<String>();
				List<AddressModel> newlyInsertedModels = new ArrayList<AddressModel>();
				List<AddressModel> updatedModels = new ArrayList<AddressModel>();
				
				String[] addressId = request.getParameterValues("addressId");
				String[] street1 = request.getParameterValues("street1");
				String[] street2 = request.getParameterValues("street2");
				String[] pincode = request.getParameterValues("pincode");
				String[] city = request.getParameterValues("city");
				String[] state = request.getParameterValues("state");
				String[] country = request.getParameterValues("country");
				
				for (int i = 0; i < addressId.length; i++) {
					afterAddressData.add(new AddressModel(addressId[i], userId, street1[i], street2[i], pincode[i],
							city[i], state[i], country[i]));
				}
				
				for(AddressModel afterAddressModel : afterAddressData) {
					
					boolean isUpdateAddress = false;
					
					for(AddressModel beforeAddressModel : beforeAddressData) {
						if(beforeAddressModel.getAddressId().equals(afterAddressModel.getAddressId())) 
						{
							
							if(!beforeAddressModel.getStreet1().equals(afterAddressModel.getStreet1()) ||
									!beforeAddressModel.getStreet2().equals(afterAddressModel.getStreet2()) ||
									!beforeAddressModel.getPincode().equals(afterAddressModel.getPincode()) ||
									!beforeAddressModel.getCity().equals(afterAddressModel.getCity()) ||
									!beforeAddressModel.getState().equals(afterAddressModel.getState()) ||
									!beforeAddressModel.getCountry().equals(afterAddressModel.getCountry())) 
							{	
								updatedModels.add(afterAddressModel);
								isUpdateAddress = true;
							}
							insertedIds.add(afterAddressModel.getAddressId());
						}
					}
					if(!isUpdateAddress && afterAddressModel.getAddressId().equals("")) 
					{
						newlyInsertedModels.add(afterAddressModel);
					}
				}
				for(AddressModel beforeAddressModel : beforeAddressData) {
					if(!insertedIds.contains(beforeAddressModel.getAddressId())) 
					{
						deletedIds.add(beforeAddressModel.getAddressId());
					}
				}
				
				aml.setAddressModelList(newlyInsertedModels);
				rs.insertAddressData(aml,userId);
				
				/*
				 * for(AddressModel model : newlyInsertedModels) {
				 * System.out.println("Insert Street1:" + model.getStreet1()); }
				 */
				
				for(AddressModel am : updatedModels) {
					rs.updateAddress(am,am.getAddressId());
					System.out.println("Update query on :: " + am.getAddressId());
				}
				for(String id:deletedIds) {
					rs.deleteAddress(id);
					System.out.println("Delete query on :: " + id);
				}
				
				
				
				
				um.setUserId(userId);
				um.setFirstName(firstName);
				um.setLastName(lastName);
				um.setDateOfBirth(dateOfBirth);
				um.setEmailId(emailId);
				um.setPassword(password);
				um.setGender(gender);
				um.setContactNo(contactNo);
				String lang = "";
				int i = 0;
				while (i < languages.length) {
					lang = languages[i] + " " + lang;
					i++;
				}
				um.setLanguages(lang);
				
				
				
				rs.updateData(um,userId);
				request.getRequestDispatcher("/home.jsp").forward(request, response);
			}
			
//			Insertion of all the data
			
			if(button.equals("insert")) {
				
				
				String userId = request.getParameter("userId");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String dateOfBirth = request.getParameter("dateOfBirth");
				String emailId = request.getParameter("emailId");
				String password = request.getParameter("password");
				String gender = request.getParameter("gender");
				String contactNo = request.getParameter("contactNo");
				String languages[] = request.getParameterValues("languages");
				
				String[] street1 = request.getParameterValues("street1");
				String[] street2 = request.getParameterValues("street2");
				String[] pincode = request.getParameterValues("pincode");
				String[] city = request.getParameterValues("city");
				String[] state = request.getParameterValues("state");
				String[] country = request.getParameterValues("country");
				
				
				UserModel um = new UserModel();
				List<AddressModel> am = new ArrayList<AddressModel>();
				AddressModelList aml = new AddressModelList();
				
//				Set input data to model(POJO)
				
				um.setUserId(userId);
				um.setFirstName(firstName);
				um.setLastName(lastName);
				um.setDateOfBirth(dateOfBirth);
				um.setEmailId(emailId);
				um.setPassword(password);
				um.setGender(gender);
				um.setContactNo(contactNo);
				String lang = "";
				int i = 0;
				while (i < languages.length) {
					lang = languages[i] + " " + lang;
					i++;
				}
				um.setLanguages(lang);
				
				for(int temp=0;temp<street1.length;temp++)
				{
					am.add(new AddressModel(null, null, street1[temp],street2[temp], pincode[temp], city[temp], state[temp], country[temp]));
				}
				aml.setAddressModelList(am);
				
//				pass model(POJO) to service method
				
				RegisterationService rs = new RegstrationServiceImpl();
				rs.insertData(um,aml);
				
//				appropriate jsp page calling
				
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<UserModel> userList = new ArrayList<>();
		List<AddressModel> addressData = new ArrayList<>();
		
		UserModel um = new UserModel();
		AddressModel am = new AddressModel("", "", "", "", "", "", "", "");
		am.setStreet1("");
		um.setUserId("");
		um.setFirstName("");
		um.setLastName("");
		um.setDateOfBirth("");
		um.setEmailId("");
		um.setPassword("");
		um.setGender("");
		um.setContactNo("");
		um.setLanguages("");

		addressData.add(am);
		userList.add(um);
		request.setAttribute("data", userList);
		request.setAttribute("addressData", addressData);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
		
	}

}
