package com.service;

import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.ImageModel;
import com.model.UserModel;

public interface RegisterationService {

	int insertData(UserModel um, ImageModel im, AddressModelList aml);

	void updateData(UserModel um, String id);

	void insertAddressData(AddressModelList aml, String userId);

	void updateAddress(AddressModel am, String addressId);

	void deleteAddress(String id);

	void updateImage(ImageModel im, String imageId);

	
}
