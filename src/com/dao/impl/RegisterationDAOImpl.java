package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.DBConnection.DatabaseConnection;
import com.dao.RegisterationDAO;
import com.model.AddressModel;
import com.model.AddressModelList;
import com.model.ImageModel;
import com.model.UserModel;

public class RegisterationDAOImpl implements RegisterationDAO {

	@Override
	public int insertData(UserModel um, ImageModel im, AddressModelList aml, String sql) {
		Connection con = null;
		PreparedStatement p = null,ps = null;
		ResultSet rs = null;
		
		int i=0;
		try {
			
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			
			p.setString(1, null);
			p.setString(2, um.getFirstName());
			p.setString(3, um.getLastName());
			p.setString(4, um.getDateOfBirth());
			p.setString(5, um.getEmailId());
			p.setString(6, um.getGender());
			p.setString(7, um.getContactNo());
			p.setString(8, um.getLanguages());
			p.setString(9, um.getPassword());
			p.setString(10, "user");
			
			i=p.executeUpdate();
			
			String s1 = "select * from user where emailId='"+um.getEmailId()+"'";
			
			ps = con.prepareStatement(s1);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("userId");
				insertAddresses(aml,userId);
				insertImage(im,userId);
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (p != null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con !=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		   }
		return i;
	}

	private void insertImage(ImageModel im, String userId) {
		
		Connection con = null;
		PreparedStatement p = null;
		
		String sql = "INSERT INTO image VALUES(?,?,?)";
		
		try {
			
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			
			p.setString(1, null);
			p.setString(2, userId);
			p.setBlob(3, im.getImage());
			
			p.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (p != null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}

	public void insertAddresses(AddressModelList aml, String userId) {
		
		Connection con = null;
		PreparedStatement p = null;
		
		con = DatabaseConnection.createConnection();
		List<AddressModel> am = aml.getAddressModelList();
		String sql = "INSERT INTO address VALUES(?,?,?,?,?,?,?,?)";
		
		for (int i = 0; i < am.size(); i++) {

			try {

				p = con.prepareStatement(sql);

				p.setString(1, null);
				p.setString(2, userId);
				p.setString(3, am.get(i).getStreet1());
				p.setString(4, am.get(i).getStreet2());
				p.setString(5, am.get(i).getPincode());
				p.setString(6, am.get(i).getCity());
				p.setString(7, am.get(i).getState());
				p.setString(8, am.get(i).getCountry());

				p.executeUpdate();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (p != null)
					try {
						p.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				if(con!=null)
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}

		}
	}

	@Override
	public int updateData(UserModel um, String sql) {
		Connection con = null;
		PreparedStatement p = null;
		int i=0;
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			
			p.setString(1, um.getFirstName());
			p.setString(2, um.getLastName());
			p.setString(3, um.getDateOfBirth());
			p.setString(4, um.getEmailId());
			p.setString(5, um.getGender());
			p.setString(6, um.getContactNo());
			p.setString(7, um.getLanguages());
			
			i=p.executeUpdate();

		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (p != null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return i;
	}

	@Override
	public int updateAddressData(AddressModel am, String sql) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement p = null;
		
		int i=0;
		try {
			con = DatabaseConnection.createConnection();
			p = con.prepareStatement(sql);
			
			p.setString(1, am.getStreet1());
			p.setString(2, am.getStreet2());
			p.setString(3, am.getPincode());
			p.setString(4, am.getCity());
			p.setString(5, am.getState());
			p.setString(6, am.getCountry());

			i = p.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (p != null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return i;
		
	}

	@Override
	public int updateImageData(ImageModel im, String sql) {
		Connection con = null;
		PreparedStatement p = null;
		con = DatabaseConnection.createConnection();
		int i=0;
		try {
			p = con.prepareStatement(sql);
			p.setBlob(1, im.getImage());

			i = p.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (p != null)
				try {
					p.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return i;
	}
}
