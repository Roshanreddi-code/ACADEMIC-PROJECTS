package com.bank.Services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.Bank.BankCustomerException;
import com.bank.DAO.BankCustomerDAO;
import com.bank.DAO.BankCustomerDAOImpl;
import com.bank.DAO.TansactionDetailsDAOImpl;
import com.bank.DAO.TransactionDetailsDAO;
import com.bank.Model.BankCustomerDetails;
import com.bank.Model.TransactionDetails;

public class BankCustomerServicesImp implements BankCustomerService {
				
    Scanner scanner=new Scanner(System.in);
    
   private BankCustomerDetails loginCustomerDetails;
   
   private BankCustomerDetails otherPersonDetails;
   
   BankCustomerDAO bankCustomerDAO=new BankCustomerDAOImpl();
   
   TransactionDetailsDAO transactionDetailsDAO=new TansactionDetailsDAOImpl();


	@Override
	public void bankCustomerDetails() {
		BankCustomerDAO bankCustomerDAO = new BankCustomerDAOImpl();
		
		List<BankCustomerDetails> allCustomerDetails=bankCustomerDAO.getAllCustomerDetails();
		
		BankCustomerDetails bankCustomerDetails=new BankCustomerDetails();
		
		System.out.println("Enter the customer name:");
		String name=scanner.next();
		bankCustomerDetails.setName(name);
		
		boolean emailIdStatus=true;
		while(emailIdStatus)
	     {
			System.out.println("Enter the customer email id:");
			String emailId=scanner.next();
			int emailIdCount=0;
		try
		{
		for(BankCustomerDetails bankCustomerDetails2:allCustomerDetails)
		{
			if(bankCustomerDetails2.getEmailId().equals(emailId))
			{
				emailIdCount++;
			}
		}
		if(emailIdCount>0)
		{
			throw new BankCustomerException("Already EmailId Existed");
		}
		else
		{
			bankCustomerDetails.setEmailId(emailId);
			emailIdStatus=false;
		}
		}
		catch(BankCustomerException bankCustomerException)
		{
			System.out.println(bankCustomerException.getExceptionMsg());
		}
	}
		
		
		System.out.println("Enter customer  mobile number:");
		long mobileNo=scanner.nextLong();
		bankCustomerDetails.setMobileNumber(mobileNo);
		
		System.out.println("Enter customer AadharNumber:");
		long aadharNumber=scanner.nextLong();
		bankCustomerDetails.setAadharNumber(aadharNumber);
		
		System.out.println("Enter the PanCardNumber(ABCDE1234Y):");
		String panCardNo=scanner.next();
		bankCustomerDetails.setPanNumber(panCardNo);
		
		System.out.println("Enterr the customer DOB(YYYY-MM-DD):");
		String dob=scanner.next();
		bankCustomerDetails.setDob(Date.valueOf(dob));
		
		System.out.println("Enter the customer Address:");
		String address=scanner.next();
		bankCustomerDetails.setAddress(address);
		
		System.out.println("Enter the customer age:");
		int age=scanner.nextInt();
		bankCustomerDetails.setAge(age);
		
		System.out.println("Enter the customer Gender:");
		String gender=scanner.next();
		bankCustomerDetails.setGender(gender);
		
		System.out.println("Enter the Amount:");
		double amount=scanner.nextDouble();
		bankCustomerDetails.setAmount(amount);
		
		bankCustomerDAO.insertBankCustomerDetails(bankCustomerDetails);
		
	}

	@Override
	public void customerLogin() {
		BankCustomerDAO bankCustomerDAO=new BankCustomerDAOImpl();
			System.out.println("Enter Customer EmailId:");
			String emilId=scanner.next();
			System.out.println("Enter Customer PIN:");
			int pin=scanner.nextInt();
			List<BankCustomerDetails> details=bankCustomerDAO.getAllCustomerDetails();
				
			loginCustomerDetails=bankCustomerDAO.selectCustomerDetailsByUsingEmailIdAndPin(emilId, pin);
			
			if(loginCustomerDetails!=null)
			{
				Boolean status=true;
				while(status)
				{
					Random random=new Random();
					String capt="";
					String a[]= {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"
							,"X","Y","Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w"
							,"x","y","z","0","1","2","3","4","5","6","7","8","9",};
					for(int i=0;i<6;i++)
					{
						int ind=random.nextInt(a.length);
						String cap=a[ind];
						capt+=cap;
					}
					System.out.println("Your Capcha:"+capt);
					System.out.println("Enter Capcha");
					String userCapcha=scanner.next();
				 if(capt.equals(userCapcha))
				 {
				 System.out.println("captcha syuccessful...");
				 
					 if(loginCustomerDetails.getGender().equalsIgnoreCase("Male"))
					 {
						 System.out.println("Hello Mr."+loginCustomerDetails.getName());
						 bankCustomerOperations();
					 }
					 if(loginCustomerDetails.getGender().equalsIgnoreCase("Female"))
					 {
						 System.out.println("Hello Ms."+loginCustomerDetails.getName());
						 bankCustomerOperations();
					 }
				   status=false;
				 }
				 else
				 {
					System.out.println("Invalid Captcha");
					System.err.println("Try agan");
				 }
				} 
			}
			else
			{
				System.out.println("Invalid EmailId or pin");
			}
		}

	@Override
	public void bankCustomerOperations() {
		System.out.println("Enter "
				+ "\n 1.For Credit "
				+ "\n 2.For Debit"
				+ " \n 3. For Check Balance"
				+ "\n 4.check statement"
				+ "\n 5.For Update Password"
				+ "\n 6.For MobileTo Mobile Transaction");
		switch(scanner.nextInt())
		{
		case 1:
			System.out.println("Credit");
			credit();
			break;
		
		case 2:
			System.out.println("Debit");
			debit();
			break;
		case 3:
			System.out.println("Current Account Balance :"+loginCustomerDetails.getAmount());
			break;
			
		case 4:
			System.out.println("Check Statement");
			CheckStatement();
			break;
		case 5:
			System.out.println("Updated password");
			updatedPassword();
			break;
		case 6:
			System.out.println("Transaction doing");
			mobileTransaction();
			break;
		}
	}
    int count=0;
	@Override
	public void debit() {
		System.out.println("Enter the Amount");
		double userAmount=scanner.nextDouble();
		if(userAmount>=0)
		{
			double  databasement=loginCustomerDetails.getAmount();
			if(userAmount<=databasement)
			{
				double balanceAmount=databasement-userAmount;
				long accountNumber=loginCustomerDetails.getAccountNumber();
				System.out.println(balanceAmount);
				if(bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(balanceAmount, accountNumber))
				{
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setTransactionType("Debit");
					transactionDetails.setTransactionDate(LocalDate.now());
					transactionDetails.setTransactionTime(LocalTime.now());
					transactionDetails.setAccountNumber(accountNumber);
					transactionDetails.setBalanceAmount(balanceAmount);
					transactionDetails.setTransationAmount(userAmount);
					transactionDetailsDAO.insertTransactionDetails(transactionDetails);
					System.out.println("Amount Debited");
				}
				else
				{
					System.out.println("Server 404");
				}
			}
			else
			{
				System.out.println("Insufficient Balance");
				count++;
				if(count==3)
				{
					System.out.println("Do you want to see your Bank Balance"
							+ "\n 1.Yes"
							+ "\n2.No");
					String option=scanner.next();
					if(option.equalsIgnoreCase("yes"))
					{
						System.out.println("Your Bank Balance is:"+loginCustomerDetails.getAmount());
					}
					count=0;
				}
				else
				{
					debit();
				}
			}
		}
		else
		{
			System.out.println("Invalid Amount");
		}
		
	}

	@Override
	public void CheckStatement() {
		
	long accountNumber=loginCustomerDetails.getAccountNumber();
		
		
		List<TransactionDetails> detailsByUsingAccountNumber=
				transactionDetailsDAO.getTransactionDetailsByCusAcNum(accountNumber);
		
		if(!detailsByUsingAccountNumber.isEmpty())
		{
			for (TransactionDetails transactionDetails : detailsByUsingAccountNumber)
			{
				System.out.println("Transaction Id:"+transactionDetails.getTransactionId());
				System.out.println("Transaction type:"+transactionDetails.getTransactionType());
				System.out.println("Transaction Date:"+transactionDetails.getTransactionDate());
				System.out.println("Transaction time:"+transactionDetails.getTransactionTime());
				System.out.println("Balane Amount:"+transactionDetails.getBalanceAmount());
				System.out.println("Transaction Amount:"+transactionDetails.getTransationAmount());
				System.out.println("!!!!!!!~~~~~~~~~~~!!!!!!!!!!!!!!!~~~~~~~~~~~~~~~~~!!!!!!!!!!!!!!!~~~~~~~~~~!!!!!!!!!!!!!");
				
			}	
		}
		
	}

	@Override
	public void updatedPassword() {
		System.out.println("Enter Your Old Pin");
		int oldPin=scanner.nextInt();
		if(loginCustomerDetails.getPin()==oldPin)
		{
			System.out.println("Enter new Pin");
			int newPin=scanner.nextInt();
			System.out.println("re-Enter your new Pin");
			int reNewPin=scanner.nextInt();
			if(newPin==reNewPin)
			{
				if(bankCustomerDAO.updatePinNumberByAcNumberAndNewPin(loginCustomerDetails.getAccountNumber(), newPin))
				{
					System.out.println("Pin updated Sucessfully");
				}
				else
				{
					System.out.println("Pin updated Failed");
				}
			}
			else
			{
				System.out.println("New pin and re-Enter pin not matches");
			}
		}
		else
		{
			System.out.println("Invalid old pin");
		}
		
	}
	
	public void credit() {
		System.out.println("Enter Amount For Credit");
		double userAmount=scanner.nextDouble();
		if(userAmount>0)
		{
			double totalAmount=userAmount+loginCustomerDetails.getAmount();
			long acNo=loginCustomerDetails.getAccountNumber();
			if(bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(totalAmount,acNo))
			{
				System.out.println("Amount Credited");
			}
			else {
				System.out.println("Server 404");
			}
		}
		else {
			System.out.println("Invalid Entered Amount");
		}
			
	}

	@Override
	public void mobileTransaction() 
	{
		 System.out.println("Enter Other Person Mobile Number");
		    long otherPersonMobileNumber = scanner.nextLong();
		    System.out.println("Enter Amount");
		    double transferAmount = scanner.nextDouble();

		     otherPersonDetails = bankCustomerDAO.selectCustomerDetailsByUsingMobileNumber(otherPersonMobileNumber);

		    if (otherPersonDetails !=null) {
		        System.out.println("Recipient Found: "+otherPersonDetails.getName());
		        if (loginCustomerDetails.getAmount() >=transferAmount) {
		            double updatedSenderBalance = loginCustomerDetails.getAmount() - transferAmount;
		            boolean isSenderUpdated = bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(updatedSenderBalance, loginCustomerDetails.getAccountNumber());
		            
		            double updatedReceiverBalance = otherPersonDetails.getAmount() + transferAmount;
		            boolean isReceiverUpdated = bankCustomerDAO.updateBalanceAmountByUsingAccountNumber(updatedReceiverBalance, otherPersonDetails.getAccountNumber());
		            
		            if (isSenderUpdated && isReceiverUpdated) {
		                loginCustomerDetails.setAmount(updatedSenderBalance);

		                // Record the transaction for the sender
		                TransactionDetails senderTransaction = new TransactionDetails();
		                senderTransaction.setTransactionType("Debit");
		                senderTransaction.setTransactionDate(LocalDate.now());
		                senderTransaction.setTransactionTime(LocalTime.now());
		                senderTransaction.setAccountNumber(loginCustomerDetails.getAccountNumber());
		                senderTransaction.setBalanceAmount(updatedSenderBalance);
		                senderTransaction.setTransationAmount(transferAmount);
		                transactionDetailsDAO.insertTransactionDetails(senderTransaction);

		                // Record the transaction for the recipient
		                TransactionDetails receiverTransaction = new TransactionDetails();
		                receiverTransaction.setTransactionType("Credit");
		                receiverTransaction.setTransactionDate(LocalDate.now());
		                receiverTransaction.setTransactionTime(LocalTime.now());
		                receiverTransaction.setAccountNumber(otherPersonDetails.getAccountNumber());
		                receiverTransaction.setBalanceAmount(updatedReceiverBalance);
		                receiverTransaction.setTransationAmount(transferAmount);
		                transactionDetailsDAO.insertTransactionDetails(receiverTransaction);

		                System.out.println("Transaction Successful!");
		                System.out.println("Amount "+ transferAmount +" debited from your account");
		                System.out.println("Amount "+ transferAmount + " transferred to " + otherPersonDetails.getName());
		            } 
		            else
		            {
		                System.out.println("Transaction Failed. Please try again.");
		            }
		        }
		       else 
		        {
		            System.out.println("Insufficient Balance!");
		        }
		    } 
		    else 
		    {
		        System.out.println("Recipient Mobile Number Not Found!");
		    }
		
				
	}
		
		
}
	
