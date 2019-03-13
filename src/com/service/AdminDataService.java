package com.service;

import java.util.ArrayList;
import java.util.List;

import com.model.AddressModel;
import com.model.ImageModel;
import com.model.UserModel;

public interface AdminDataService {

	ArrayList<UserModel> retriveData(UserModel um);

	ArrayList<UserModel> retriveUserData(String id);

	void deleteData(String id);

	List<AddressModel> retriveAddressData(String id);

	List<ImageModel> retriveImageData(String id) throws Exception;
	
	int isEmailExists(String emailId);

	List<UserModel> forgotData(String emailId);

}
