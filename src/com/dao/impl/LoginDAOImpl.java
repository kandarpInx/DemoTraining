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

	/*
	@Override
	public String isAuthenticated(UserModel um) {
		// TODO Auto-generated method stub

		String emailId = um.getEmailId();
		String password = um.getPassword();

		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String emailIdDB = "";
		String passwordDB = "";
		String roleDB = "";

		try {
			con = DatabaseConnection.createConnection();
			statement = con.createStatement();
			resultSet = statement.executeQuery("select * from user");

			while (resultSet.next()) {
				emailIdDB = resultSet.getString("emailId");
				passwordDB = resultSet.getString("password");
				roleDB = resultSet.getString("role");

				if (emailId.equals(emailIdDB) && password.equals(passwordDB) && roleDB.equals("admin")) {
					return "admin";
				} else if (emailId.equals(emailIdDB) && password.equals(passwordDB) && roleDB.equals("user")) {
					return "user";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "Invalid user credentials"; // Just returning appropriate message otherwise

	}*/

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
		if(rowCount == 0) {
			userDetails.put("role", "Invalid user credentials");
		}
	
		return userDetails;
	}

}
