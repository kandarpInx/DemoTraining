package com.model;

public class AddressModel {
	
	private String addressId="0";
	private String userId;
	private String street1;
	private String street2;
	private String pincode;
	private String city;
	private String state;
	private String country;
	public AddressModel() {}
	
	public AddressModel(String addressId, String userId, String street1, String street2, String pincode,
			 String city,  String state, String country) {
		this.addressId = addressId;
		this.userId = userId;
		this.street1 = street1;
		this.street2 = street2;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStreet1() {
		return street1;
	}
	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	public String getStreet2() {
		return street2;
	}
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
