package com.service.impl;

import java.util.Map;

import com.dao.LoginDAO;
import com.dao.impl.LoginDAOImpl;
import com.model.UserModel;
import com.service.LoginService;

public class LoginServiceImpl implements LoginService {

	/*
	@Override
	public String isValidate(UserModel um) {
		// TODO Auto-generated method stub
		
		LoginDAO ld = new LoginDAOImpl();
		String userValidate = ld.isAuthenticated(um);
		
		return userValidate;
	}*/

	@Override
	public Map<String, String> getUserDetails(UserModel um) {
		LoginDAO ld = new LoginDAOImpl();
		Map<String, String> userDetails = ld.getUserDetails(um);
		
		return userDetails;
	}
}
