package com.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.dao.DataRetriveDAO;
import com.dao.impl.DataRetriveDAOImpl;
import com.model.AddressModel;
import com.model.UserModel;
import com.service.AdminDataService;

public class AdminDataServiceImpl implements AdminDataService {

	@Override
	public ArrayList<UserModel> retriveData(UserModel um) {
		
		String sql = "Select * from user";
		
		DataRetriveDAO drd = new DataRetriveDAOImpl();
		ArrayList<UserModel> data = null;
		try {
			data = drd.retriveData(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}

	@Override
	public ArrayList<UserModel> retriveUserData(String id) {
		
		String sql = "Select * from user where userId = '"+id+"'";
		
		DataRetriveDAO drd = new DataRetriveDAOImpl();
		ArrayList<UserModel> data = null;
		try {
			data = drd.retriveData(sql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	@Override
	public List<AddressModel> retriveAddressData(String id) {
		
		String sql = "Select * from address where userId = '"+id+"'";
		
		DataRetriveDAO drd = new DataRetriveDAOImpl();
		List<AddressModel> addressData = null;
		addressData = drd.retriveAddressData(sql);
		
		return addressData;
	}

	@Override
	public void deleteData(String id) {
		
		String sql = "delete from user where userId = '"+id+"'";
		DataRetriveDAO drd = new DataRetriveDAOImpl();
		int data = drd.removeData(sql);
		
		if(data!=0) {
			System.out.println("Delete user successfully..");
		}
		else {
			System.out.println("Error in deleting user data..");
		}
	}
}
