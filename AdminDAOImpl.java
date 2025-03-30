package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl implements AdminDAO {
	private static final String select="select * from Admin where email_id=? and password=?";
	private static final String url="jdbc:mysql://localhost:3306/java_project?user=root&password=tiger@1234";

	@Override
	public boolean selectAdminDetailsByUsingEmailIdAndPassword(String emailId,String password) 
	{
		
		//selection
		try {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement prepareStatement=connection.prepareStatement(select);
			prepareStatement.setString(1, emailId);
			prepareStatement.setString(2,password);
			ResultSet resultSet=prepareStatement.executeQuery();
			if(resultSet.next())
			{
//				System.out.println("Login successfully....");
				return true;
			}
			else
			{
//				System.out.println("Invalid EmailId or password...");
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
}
