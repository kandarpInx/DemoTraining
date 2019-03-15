package com.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.DBConnection.DatabaseConnection;
import com.dao.DataRetriveDAO;
import com.model.AddressModel;
import com.model.ImageModel;
import com.model.UserModel;

public class DataRetriveDAOImpl implements DataRetriveDAO {

	public ArrayList<UserModel> retriveData(String sql) throws ClassNotFoundException {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		
		ArrayList<UserModel> userList = new ArrayList<>();
		
		try {
			
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			
			while(rs.next())
			{
				UserModel usm = new UserModel();
				
				usm.setUserId(rs.getString("userId"));
				usm.setFirstName(rs.getString("firstName"));				
				usm.setLastName(rs.getString("lastName"));
				usm.setDateOfBirth(rs.getString("dateOfBirth"));
				usm.setEmailId(rs.getString("emailId"));
				usm.setGender(rs.getString("gender"));
				usm.setContactNo(rs.getString("contactNo"));
				usm.setLanguages(rs.getString("languages"));
				usm.setPassword(rs.getString("password"));
				usm.setRole(rs.getString("role"));
				
				userList.add(usm);
			}
	   
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(p!=null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		 return userList;
	}

	@Override
	public int removeData(String sql) {
		Connection con = null;
		PreparedStatement p = null;
		
		int i=0;
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			i = p.executeUpdate();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(p!=null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		return i;
	}

	@Override
	public List<AddressModel> retriveAddressData(String sql) {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		
		List<AddressModel> am = new ArrayList<AddressModel>();
		
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			
			String addressId, userId, street1, street2, pincode, city, state, country;
			
			while(rs.next())
			{
				addressId = rs.getString("addressId");
				userId = rs.getString("userId");
				street1 = rs.getString("street1");
				street2 = rs.getString("street2");
				pincode = rs.getString("pincode");
				city = rs.getString("city");
				state = rs.getString("state");
				country = rs.getString("country");
				
				am.add(new AddressModel(addressId, userId, street1, street2, pincode, city, state, country));
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(p!=null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		
		return am;
	}

	@Override
	public List<ImageModel> retriveImageData(String sql) throws Exception {
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		List<ImageModel> im = new ArrayList<ImageModel>();
		ImageModel img = new ImageModel();
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			
			while(rs.next())
			{
				String userId = rs.getString("userId");
				String imageId = rs.getString("imageId");
				Blob blob = rs.getBlob("image");
				 
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				 
				while ((bytesRead = inputStream.read(buffer)) != -1) {
				    outputStream.write(buffer, 0, bytesRead);
				}
				 
				byte[] imageBytes = outputStream.toByteArray();
				 
				String image = Base64.getEncoder().encodeToString(imageBytes);
				 
				inputStream.close();
				outputStream.close();
				
				img.setUserId(userId);
				img.setImageId(imageId);
				img.setImageString(image);
				
				im.add(img);
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(p!=null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		
		return im;
	}

	@Override
	public int existedEmail(String sql) {
		
		Connection con = null;
		PreparedStatement p = null;
		ResultSet rs = null;
		
		
		int count=0;
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			rs = p.executeQuery();
			while(rs.next()) {
				count = rs.getInt("rowcount");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(p!=null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		   }
		
		return count;
	}
}
