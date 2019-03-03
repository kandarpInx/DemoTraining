package com.dao;

import java.util.Map;

import com.model.UserModel;

public interface LoginDAO {

	//String isAuthenticated(UserModel um);
	Map<String, String> getUserDetails(UserModel um);

}
