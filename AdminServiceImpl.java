package com.bank.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.AdminDAO;
import com.bank.DAO.AdminDAOImpl;
import com.bank.DAO.BankCustomerDAO;
import com.bank.DAO.BankCustomerDAOImpl;
import com.bank.Model.BankCustomerDetails;

public class AdminServiceImpl implements AdminService {
  Scanner sc=new Scanner(System.in);
  AdminDAO adminDAO=new AdminDAOImpl();
  BankCustomerDAO bankCustomerDAO=new BankCustomerDAOImpl();
	@Override
	public void adminLogin() {
		System.out.println("Enter the Admin EmailId:");
		String emailId=sc.next();
		System.out.println("Enter Admin Password:");
		String password=sc.next();
		if(adminDAO.selectAdminDetailsByUsingEmailIdAndPassword(emailId, password))
		{
			System.out.println("Enter \n 1.to Get All Account Request Details "
					+ "\n 2. To get all user Details" 
					+ "\n 3. To get all account closing Request Details");
			switch(sc.nextInt())
			{
			case 1:
				System.out.println("All Account Request Details");
				allPendingDetails();
				break;
				
			case 2:
				System.out.println("All user Details");
				allUserDetails();
				break;
				
			case 3:
				System.out.println("All Account closing Request Details");
				break;
				
			default:
				System.out.println("Invalid Request");
				break;
			}
		}
		
	}
	int count=1;
	@Override
	public void allUserDetails() {
		//get the method from bank customerDAO class
		List<BankCustomerDetails> allCustomerDetails=bankCustomerDAO.getAllCustomerDetails();
		allCustomerDetails.forEach((customerDetails)->{
			System.out.println("S.No:"+count);
			
			System.out.println("Customer Id:"+customerDetails.getId());
			System.out.println("Customer Name:"+customerDetails.getName());
			System.out.println("Customer EmailId:"+customerDetails.getEmailId());
			String str=""+customerDetails.getMobileNumber();
			str=str.substring(1, 5)+"*****"+str.substring(str.length()-3, str.length());
			System.out.println("Customer Mobile Number:"+str);
			System.out.println("Customer Aadhar Number:"+customerDetails.getAccountNumber());
			System.out.println("Customer status:"+customerDetails.getStatus());
			System.out.println("*******-----------**************-------------*****************");
			count++;
		});
		
	}
	@Override
	public void allPendingDetails() {
		List<BankCustomerDetails> allCustomerDetails=bankCustomerDAO.getAllCustomerDetails();
		List<BankCustomerDetails> allPendingDetaillsList=new ArrayList<BankCustomerDetails>();
		
		for(BankCustomerDetails bankCustomerDetails:allCustomerDetails)
		{
			
			if(bankCustomerDetails.getStatus().equalsIgnoreCase("Pending"))
			{
				BankCustomerDetails bankCustomerDetails2=new BankCustomerDetails();
				bankCustomerDetails2.setId(bankCustomerDetails.getId());
				bankCustomerDetails2.setName(bankCustomerDetails.getName());
				bankCustomerDetails2.setEmailId(bankCustomerDetails.getEmailId());
				allPendingDetaillsList.add(bankCustomerDetails2);
				
				int indexOf=allPendingDetaillsList.indexOf(bankCustomerDetails2)+1;
				
				System.out.println("S.no:"+indexOf);
//				System.out.println("Customer Id:"+bankCustomerDetails.getId());
				System.out.println("Customer Name:"+bankCustomerDetails.getName());
				System.out.println("Customer emailId:"+bankCustomerDetails.getEmailId());
				System.out.println("Customer Mobile Number:"+bankCustomerDetails.getMobileNumber());
				System.out.println("Customer status:"+bankCustomerDetails.getStatus());
				
			}
			
		}
		System.out.println("Enter  S.No To select the Customer details:");
		BankCustomerDetails bankCustomerDetails=allPendingDetaillsList.get(sc.nextInt()-1);
		System.out.println(bankCustomerDetails);
		
		acceptPendingDetails(bankCustomerDetails);
		
//		int SNo=sc.nextInt();
//		for(BankCustomerDetails bankCustomerDetails:allCustomerDetails)
//		{
//			if(bankCustomerDetails.getStatus().equalsIgnoreCase("Pending"))
//			{
//		     int indexOf=allCustomerDetails.indexOf(bankCustomerDetails)+1;
//		     if(SNo==indexOf)
//		     {
//			 System.out.println( bankCustomerDetails.toString());
//			 break;
//		     }
//			}
//		 }
	}
	@Override
	public void acceptPendingDetails(BankCustomerDetails bankCustomerDetails) {
		Random random=new Random();
		//int max=10000000;
		//int min=1000000;
		int accountingNumber=random.nextInt(10000000);
//		System.out.println(accountingNumber);
		if(accountingNumber<1000000)
		{
			accountingNumber+=1000000;
		}
		System.out.println(accountingNumber);
		
		int pin=random.nextInt(10000);
		if(pin<1000)
		{
			pin+=1000;
		}
		System.out.println(pin);
		bankCustomerDetails.setAccountNumber(accountingNumber);
		bankCustomerDetails.setPin(pin);
		bankCustomerDAO.updateAccountNumberAndPinByUsingId(bankCustomerDetails);
			
	}

}
