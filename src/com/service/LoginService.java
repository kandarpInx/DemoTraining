package com.service;

import java.util.Map;

import com.model.UserModel;

public interface LoginService {

	//String isValidate(UserModel um);
	Map<String, String> getUserDetails(UserModel um);
}
