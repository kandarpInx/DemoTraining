package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection.DatabaseConnection;
import com.dao.DataRetriveDAO;
import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.UserModel;

public class DataRetriveDAOImpl implements DataRetriveDAO {

	public ArrayList<UserModel> retriveData(String sql) throws ClassNotFoundException {
		ArrayList<UserModel> userList = new ArrayList<>();
		try {
			Connection con = null;
			con = DatabaseConnection.createConnection();
			PreparedStatement p = con.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while(rs.next())
			{
				UserModel usm = new UserModel();
				
				usm.setUserId(rs.getString("userId"));
				usm.setFirstName(rs.getString("firstName"));				
				usm.setLastName(rs.getString("lastName"));
				usm.setDateOfBirth(rs.getString("dateOfBirth"));
				usm.setEmailId(rs.getString("emailId"));
				usm.setGender(rs.getString("gender"));
				usm.setContactNo(rs.getString("contactNo"));
				usm.setLanguages(rs.getString("languages"));
				usm.setPassword(rs.getString("password"));
	        
				userList.add(usm);
			}
	   
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		 return userList;
	}

	@Override
	public int removeData(String sql) {
		int i=0;
		try {
			Connection con = null;
			con = DatabaseConnection.createConnection();
			PreparedStatement p = con.prepareStatement(sql);
			i = p.executeUpdate();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<AddressModel> retriveAddressData(String sql) {
		
		Connection con = null;
		con = DatabaseConnection.createConnection();
		List<AddressModel> am = new ArrayList<AddressModel>();
		AddressModelList aml = new AddressModelList();
		try {
			PreparedStatement p = con.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			String addressId, userId, street1, street2, pincode, city, state, country;
			
			while(rs.next())
			{
				addressId = rs.getString("addressId");
				userId = rs.getString("userId");
				street1 = rs.getString("street1");
				street2 = rs.getString("street2");
				pincode = rs.getString("pincode");
				city = rs.getString("city");
				state = rs.getString("state");
				country = rs.getString("country");
				
				am.add(new AddressModel(addressId, userId, street1, street2, pincode, city, state, country));
			}
			aml.setAddressModelList(am);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return am;
	}
}
