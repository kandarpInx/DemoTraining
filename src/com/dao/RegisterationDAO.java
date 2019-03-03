package com.dao;

import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.UserModel;

public interface RegisterationDAO {

	int insertData(UserModel um, AddressModelList aml, String sql);

	int updateData(UserModel um, String sql);

	void insertAddresses(AddressModelList aml, String userId);

	int updateAddressData(AddressModel am, String sql);

	int removeAddressData(String sql);

	//int insAddrData(UserModel um, AddressModel am, String sql);

}
