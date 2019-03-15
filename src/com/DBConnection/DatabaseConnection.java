package com.DBConnection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

	public static Connection createConnection() {
		Connection con = null;
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("database.properties");
			
			Properties props = new Properties();
			
			props.load(input);
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //loading mysql driver
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
			System.out.println("Connection established");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(SQLException s) {
			s.printStackTrace();
		}
		return con; 
	 }

}
