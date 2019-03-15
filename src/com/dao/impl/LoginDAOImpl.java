package com.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.dao.LoginDAO;
import com.model.UserModel;
import com.DBConnection.DatabaseConnection;

public class LoginDAOImpl implements LoginDAO {

	@Override
	public Map<String, String> getUserDetails(UserModel um) {
		
		Map<String, String> userDetails = new HashMap<String, String>();
	
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String roleDB = "";
		String userId = "";
		String firstName = "";
		
		int rowCount = 0;
		try {
			con = DatabaseConnection.createConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery("select * from user where emailId='" +um.getEmailId()+ "' and password='" +um.getPassword()+ "'");
			while (resultSet.next()) {
				rowCount++;
				roleDB = resultSet.getString("role");
				userId = resultSet.getString("userId");
				firstName = resultSet.getString("firstName");
				
				if(roleDB.equals("admin")) {
					userDetails.put("role", "admin");
				} else {
					userDetails.put("role", "user");
				}
				userDetails.put("firstName", firstName);
				userDetails.put("userId", userId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(resultSet!=null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
		if(rowCount == 0) {
			userDetails.put("role", "Invalid user credentials");
		}
	
		return userDetails;
	}

}
