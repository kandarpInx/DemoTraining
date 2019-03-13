package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.ImageModel;
import com.model.UserModel;
import com.service.AdminDataService;
import com.service.RegisterationService;
import com.service.impl.AdminDataServiceImpl;
import com.service.impl.RegstrationServiceImpl;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
@MultipartConfig
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
		String button = request.getParameter("btnValue");
		
		HttpSession session = request.getSession();
		
		if(session!=null) {
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

			if(button.equals("delete")) {
				String id = request.getParameter("userId");
				ad.deleteData(id);
				response.sendRedirect(request.getContextPath() + "/AdminDataServlet");
			}
			
			if(button.equals("update1")) {
				String id = request.getParameter("userId");
				
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
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
			
			if(button.equals("update")) {
				
				RegisterationService rs = new RegstrationServiceImpl();
				
				String userId = request.getParameter("userId");
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String dateOfBirth = request.getParameter("dateOfBirth");
				String emailId = request.getParameter("emailId");
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
				um.setGender(gender);
				um.setContactNo(contactNo);
				String lang = "";
				int i = 0;
				while (i < languages.length) {
					lang = languages[i] + "," + lang;
					i++;
				}
				um.setLanguages(lang);
				
				
				InputStream inputStream = null;
				Part filePart = request.getPart("image");
				String imageId = request.getParameter("imageId");
				if(filePart!=null) {
					inputStream = filePart.getInputStream();
				}
				
				ImageModel im = new ImageModel();
				if(inputStream!=null) {
					im.setImage(inputStream);
					im.setUserId(userId);
				}
				
				
				rs.updateImage(im,imageId);
				rs.updateData(um,userId);
				response.sendRedirect(request.getContextPath() + "/AdminDataServlet");
			}
			
			if(button.equals("logout")) {
				session.invalidate();
				
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        response.setDateHeader("Expires", 0);
		        
				/*
				 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
				 * out.println("<script type=\"text/javascript\">");
				 * out.println("alert('Logged out successful...');"); out.println("</script>");
				 */
		        
	        	request.setAttribute("error", "Logged out successfully");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.include(request, response);
				System.out.println("Logged out");
			}
		}
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
			
			InputStream inputStream = null;
			Part filePart = request.getPart("image");
			if(filePart!=null) {
				inputStream = filePart.getInputStream();
			}
			
			
			UserModel um = new UserModel();
			List<AddressModel> am = new ArrayList<AddressModel>();
			AddressModelList aml = new AddressModelList();
			ImageModel im = new ImageModel();
//			Set input data to model(POJO)
			
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
				lang = languages[i] + "," + lang;
				i++;
			}
			um.setLanguages(lang);
			
			if(inputStream!=null) {
				im.setImage(inputStream);
			}
			
			
			for(int temp=0;temp<street1.length;temp++)
			{
				am.add(new AddressModel(null, null, street1[temp],street2[temp], pincode[temp], city[temp], state[temp], country[temp]));
			}
			aml.setAddressModelList(am);
			
//			pass model(POJO) to service method
			
			RegisterationService rs = new RegstrationServiceImpl();
			int data = rs.insertData(um,im,aml);
			
			if(data!=0) {
	        	request.setAttribute("error", "Registration Successfully Completed");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.include(request, response);
			}
			else {
	        	request.setAttribute("error", "Registration failed");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.include(request, response);
			}
			
//			appropriate jsp page calling
			
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		if(button.equals("forgot")) {
			AdminDataService ad = new AdminDataServiceImpl();
			String emailId = request.getParameter("emailId");
			UserModel um = new UserModel();
			um.setEmailId(emailId);
			List<UserModel> forgotData = ad.forgotData(emailId);
			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			pw.println("<script type=\"text/javascript\">");
			pw.println("alert('Password is "+forgotData.get(0).getPassword()+"' );");
			pw.println("</script>");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);

		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<UserModel> userList = new ArrayList<>();
		List<AddressModel> addressData = new ArrayList<>();
		List<ImageModel> imageData = new ArrayList<>();
		ImageModel im = new ImageModel();
		
		UserModel um = new UserModel();
		AddressModel am = new AddressModel();

		
		addressData.add(am);
		userList.add(um);
		imageData.add(im);
		request.setAttribute("imageData", imageData);
		request.setAttribute("data", userList);
		request.setAttribute("addressData", addressData);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
		
	}

}
