package com.service.impl;

import com.dao.RegisterationDAO;
import com.dao.impl.RegisterationDAOImpl;
import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.UserModel;
import com.service.RegisterationService;

public class RegstrationServiceImpl implements RegisterationService {

	@Override
	public void insertData(UserModel um, AddressModelList aml) {
		
		String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		RegisterationDAO rg = new RegisterationDAOImpl();
		int data = rg.insertData(um, aml, sql);
		
		System.out.println(data);
		if(data!=0) {
			System.out.println("Success user..");
		}
		else {
			System.out.println("Failed user...");
		}

	}

	@Override
	public void updateData(UserModel um, String id) {
		
		String sql = "update user set firstName=?,lastName=?,dateOfBirth=?,emailId=?,gender=?,contactNo=?,languages=?,password=? where userId="+id;
		
		RegisterationDAO rg = new RegisterationDAOImpl();
		int data = rg.updateData(um, sql);
		
		if(data!=0) {
			System.out.println("Updated successfully..");
		}
		else {
			System.out.println("Not updated...");
		}
	}

	@Override
	public void insertAddressData(AddressModelList aml, String userId) {
		
		RegisterationDAO rg = new RegisterationDAOImpl();
		rg.insertAddresses(aml, userId);
		
	}

	@Override
	public void updateAddress(AddressModel am, String addressId) {
		
		String sql = "update address set street1=?, street2=?, pincode=?, city=?, state=?, country=? where addressId = "+addressId;

		RegisterationDAO rg = new RegisterationDAOImpl();
		int msg = rg.updateAddressData(am, sql);
		
		if(msg!=0) {
			System.out.println("Update operation on address id "+addressId+" completed successfully..");
		}
		else {
			System.out.println("Error in updating address data...");
		}
	}

	@Override
	public void deleteAddress(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from address where addressId = '"+id+"'";
		RegisterationDAO rg = new RegisterationDAOImpl();
		int msg = rg.removeAddressData(sql);
		
		if(msg!=0) {
			System.out.println("Delete address on address id "+id+" successfully...");
		}
		else {
			System.out.println("Error in deleting user data..");
		}
	}
}
