package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.Model.BankCustomerDetails;

public class BankCustomerDAOImpl implements BankCustomerDAO {
	
	private static final String select_otherPerson = "select * from bank_customer_details where Mobile_Number=?";
	
	 private static final String select="select * from Bank_Customer_Details";
	 
	private static final String inser_customer_details="jdbc:mysql://localhost:3306/java_project?user=root&password=tiger@1234";
	
private static final String insert="insert into Bank_Customer_Details(Name, EmailId, Mobile_Number, Aadhar_Number, Pan_Number, Date_Of_Birth, Address, Amount, Age, Gender,status) values(?,?,?,?,?,?,?,?,?,?,?)";

private static final String update_pin_AccountNumber="update Bank_Customer_Details set Account_number=?,Pin=?,status=? where id=?";

private static final String delete_customer_details="delete from Bank_Customer_Details where id=?";

private static final String customer_Login="select * from Bank_Customer_Details where EmailId=? And pin=?";

private static final String  update_Amount="update Bank_Customer_Details set Amount=? where Account_number=?";

private static final String update_Pin_Number="update  Bank_Customer_Details set Pin=? where Account_number=?";
	@Override
	public void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails) {
		
		try {
			Connection connection=DriverManager.getConnection(inser_customer_details);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setString(1,bankCustomerDetails.getName());
			preparedStatement.setString(2,bankCustomerDetails.getEmailId());
			preparedStatement.setLong(3,bankCustomerDetails.getMobileNumber());
			preparedStatement.setLong(4, bankCustomerDetails.getAadharNumber());
			preparedStatement.setString(5, bankCustomerDetails.getPanNumber());
			preparedStatement.setDate(6, bankCustomerDetails.getDob());
			preparedStatement.setString(7, bankCustomerDetails.getAddress());
			preparedStatement.setDouble(8, bankCustomerDetails.getAmount());
			preparedStatement.setInt(9,bankCustomerDetails.getAge());
			preparedStatement.setString(10,bankCustomerDetails.getGender());
			preparedStatement.setString(11,"Pending" );
			int res=preparedStatement.executeUpdate();
			if(res>0)
			{
				System.out.println("data entered successfully...");
				
			}
			else
			{
				System.out.println("invalid data..");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public List<BankCustomerDetails> getAllCustomerDetails() {
		//selection
		try {
			Connection connection=DriverManager.getConnection(inser_customer_details);
			PreparedStatement prepareStatement=connection.prepareStatement(select);
			ResultSet resultSet=prepareStatement.executeQuery();
			List<BankCustomerDetails> listOfBankCustomerDetails=new ArrayList<BankCustomerDetails>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					BankCustomerDetails bankCustomerDetails=new BankCustomerDetails();
					bankCustomerDetails.setId(resultSet.getInt("id"));
					bankCustomerDetails.setName(resultSet.getString("Name"));
					bankCustomerDetails.setEmailId(resultSet.getString("EmailId"));
					bankCustomerDetails.setAadharNumber(resultSet.getLong("Aadhar_Number"));
					bankCustomerDetails.setMobileNumber(resultSet.getLong("Mobile_Number"));
					bankCustomerDetails.setPanNumber(resultSet.getString("Pan_Number"));
					bankCustomerDetails.setStatus(resultSet.getString("status"));
					listOfBankCustomerDetails.add(bankCustomerDetails);		
					
					
				}
				return listOfBankCustomerDetails;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	@Override
	public void updateAccountNumberAndPinByUsingId(BankCustomerDetails bankCustomerDetails) {
		try {
			Connection connection=
			DriverManager.getConnection(inser_customer_details);
			PreparedStatement preparedStatement=connection.prepareStatement(update_pin_AccountNumber);
			preparedStatement.setLong(1, bankCustomerDetails.getAccountNumber());
			preparedStatement.setInt(2, bankCustomerDetails.getPin());
			preparedStatement.setInt(4, bankCustomerDetails.getId());
			preparedStatement.setString(3, "Accepted");
			int result=preparedStatement.executeUpdate();
			if(result>0)
			{
				System.out.println("updated");
			}
			else
			{
				System.out.println("not updated");
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void deleteCustomerDetailsByUsingId(BankCustomerDetails bankCustomerDetails) {
		try {
			Connection connection=DriverManager.getConnection(inser_customer_details);
			PreparedStatement preparedStatement=connection.prepareStatement(delete_customer_details);
			preparedStatement.setInt(1, bankCustomerDetails.getId());
			int result=preparedStatement.executeUpdate();
			if(result>0)
			{
				System.out.println("Deleted");
			}
			else
			{
				System.out.println("Not deleted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	public BankCustomerDetails selectCustomerDetailsByUsingEmailIdAndPin(String emailId, int pin) {
		
		try {
			Connection connection=DriverManager.getConnection(inser_customer_details);
			PreparedStatement preparedStatement=connection.prepareStatement(customer_Login);
			preparedStatement.setString(1, emailId);
			preparedStatement.setInt(2, pin);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				BankCustomerDetails bankCustomerDetails=new BankCustomerDetails();
				bankCustomerDetails.setAccountNumber(resultSet.getLong("Account_number"));
				bankCustomerDetails.setName(resultSet.getString("Name"));
				bankCustomerDetails.setGender(resultSet.getString("Gender"));
				bankCustomerDetails.setAmount(resultSet.getDouble("Amount"));
				bankCustomerDetails.setPin(resultSet.getInt("Pin"));
           return bankCustomerDetails;			
			}
			else
			{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public boolean updateBalanceAmountByUsingAccountNumber(double amount, long accountNumber) {
				try {
					Connection connection=DriverManager.getConnection(inser_customer_details);
					PreparedStatement preparedStatement=connection.prepareStatement(update_Amount);
					preparedStatement.setDouble(1, amount);
					preparedStatement.setLong(2, accountNumber);
					int res=preparedStatement.executeUpdate();
					if(res>0)
					{
						return true;
					}
					else
					{
						return false;
					}
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
				
	}
	@Override
	public BankCustomerDetails selectCustomerDetailsByUsingMobileNumber(long mobileNumber) {
		
		 BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
		    try {
		        Connection connection = DriverManager.getConnection(inser_customer_details);
		        PreparedStatement preparedStatement = connection.prepareStatement(select_otherPerson);
		        preparedStatement.setLong(1, mobileNumber);
		        ResultSet resultSet = preparedStatement.executeQuery(); // Use executeQuery() for SELECT
		        if (resultSet.next()) {
		            bankCustomerDetails.setId(resultSet.getInt("id"));
		            bankCustomerDetails.setName(resultSet.getString("Name"));
		            bankCustomerDetails.setEmailId(resultSet.getString("EmailId"));
		            bankCustomerDetails.setMobileNumber(resultSet.getLong("Mobile_Number"));
		            bankCustomerDetails.setAmount(resultSet.getDouble("Amount"));
		            bankCustomerDetails.setAccountNumber(resultSet.getInt("Account_Number"));;
		            bankCustomerDetails.setPin(resultSet.getInt("Pin"));
		            
		          return bankCustomerDetails;
		        } 
		        else
		        {
		            return null; 
		        }
		    } catch (SQLException e)
		    {
		        e.printStackTrace();
		        return null;
		    }
	}
	@Override
	public boolean updatePinNumberByAcNumberAndNewPin(long accountNumber, int newPin) {
		
		try
		{
			Connection connection=DriverManager.getConnection(inser_customer_details);
			PreparedStatement preparedStatement=connection.prepareStatement(update_Pin_Number);
			preparedStatement.setInt(1, newPin);
			preparedStatement.setLong(2, accountNumber);
			int res=preparedStatement.executeUpdate();
			if(res>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
