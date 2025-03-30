package com.bank.DAO;

	
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.bank.Model.TransactionDetails;


public  class TansactionDetailsDAOImpl implements TransactionDetailsDAO
{
	private static final String url="jdbc:mysql://localhost:3306/java_project?user=root&password=tiger@1234";
	
	private static final String setting_transaction_details="insert into transaction_details(Transaction_Type, Transaction_Date, Transaction_Time, Balance_Amount, Transaction_Amount, Account_Number)values (?,?,?,?,?,?)";
	
	private static final String get_transaction_details="select * from transaction_details where Account_Number=?";
	@Override
	public List<TransactionDetails> getTransactionDetailsByCusAcNum(long acNum)
	{
		List<TransactionDetails> list = new ArrayList<TransactionDetails>();
		
		try
	    {
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(get_transaction_details);
			preparedStatement.setLong(1,acNum);
			ResultSet result=preparedStatement.executeQuery();
			while(result.next())
			{
				TransactionDetails td = new TransactionDetails();
				td.setAccountNumber(result.getLong("Account_Number"));
				td.setBalanceAmount(result.getDouble("Balance_Amount"));
				td.setTransactionDate(result.getDate("Transaction_Date").toLocalDate());
				td.setTransactionId(result.getInt("Transaction_id"));
				td.setTransactionTime(result.getTime("Transaction_Time").toLocalTime());
				td.setTransactionType(result.getString("Transaction_Type"));
				td.setTransationAmount(result.getDouble("Transaction_Amount"));
				list.add(td);
			}
	    }
		catch (SQLException e)
		{
			e.printStackTrace();
			
		}
		return list;
	}
	
	
	
	
	public void insertTransactionDetails(TransactionDetails transactionsDetails) {
		
		TransactionDetails transactionDetails = new TransactionDetails();
			try {
				Connection connection=DriverManager.getConnection(url);
				PreparedStatement preparedStatement= connection.prepareStatement(setting_transaction_details);
				preparedStatement.setString(1,transactionsDetails.getTransactionType());
				// localdate into date we use this method
				preparedStatement.setDate(2,Date.valueOf(transactionsDetails.getTransactionDate()));
				preparedStatement.setTime(3,Time.valueOf(transactionsDetails.getTransactionTime()));
				preparedStatement.setDouble(4,transactionsDetails.getBalanceAmount());
				preparedStatement.setDouble(5,transactionsDetails.getTransationAmount());
				preparedStatement.setLong(6,transactionsDetails.getAccountNumber());
				
				int result=preparedStatement.executeUpdate();
				
					
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		
	}
