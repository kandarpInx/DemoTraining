package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.AddressModel;
import com.model.ImageModel;
import com.model.UserModel;

public interface DataRetriveDAO {

	ArrayList<UserModel> retriveData(String sql) throws ClassNotFoundException, SQLException;

	int removeData(String sql);

	List<AddressModel> retriveAddressData(String sql);

	List<ImageModel> retriveImageData(String sql) throws Exception;

	int existedEmail(String sql);

}
