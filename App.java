package com.bank.Bank;

import java.util.Scanner;

import com.bank.Services.AdminService;
import com.bank.Services.AdminServiceImpl;
import com.bank.Services.BankCustomerService;
import com.bank.Services.BankCustomerServicesImp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BankCustomerService bankCustomerService = new BankCustomerServicesImp();
    	AdminService adminService=new AdminServiceImpl();
    	
    	
    	Scanner sc=new Scanner(System.in);
    	boolean status=true;
    	while(status)
    	{
    		//adminService.acceptPendingDetails();
    		System.out.println("Enter \n 1.for Customer Registration "
    				+ "\n 2.for Customer Login "
    				+ "\n 3.for Admin Login");
    				
    		switch(sc.nextInt())
    		{
    		case 1:
    			System.out.println("Customer Registration");
    			bankCustomerService.bankCustomerDetails();
    			break;
    			
    		case 2:
    			System.out.println("Customer Login");
    			bankCustomerService.customerLogin();
    			break;
    			
    		case 3:
    			System.out.println("Admin Login");
    			adminService.adminLogin();
    			break;
    			
    		default:
    			System.out.println("Invalid request");
    			break;
    		}
    		System.out.println("Do You want to Continue Say \n yes \n No");
    		if(sc.next().equalsIgnoreCase("yes"))
    		{
    			
    		}
    		else
    		{
    			System.out.println("Thank You....");
    			status=false;
    		}
    	}
    	
    }
}
