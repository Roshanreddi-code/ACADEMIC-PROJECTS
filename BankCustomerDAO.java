package com.bank.DAO;

import java.util.List;

import com.bank.Model.BankCustomerDetails;

public interface BankCustomerDAO {
	
  void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails);
  
  BankCustomerDetails selectCustomerDetailsByUsingMobileNumber(long mobileNumber);
  
  public List<BankCustomerDetails> getAllCustomerDetails();
  
  void updateAccountNumberAndPinByUsingId(BankCustomerDetails bankCustomerDetails);
  
  void deleteCustomerDetailsByUsingId(BankCustomerDetails bankCustomerDetails);
  
  BankCustomerDetails selectCustomerDetailsByUsingEmailIdAndPin(String emailId,int pin);
  
  boolean updateBalanceAmountByUsingAccountNumber(double amount,long accountNumber);
  
  boolean updatePinNumberByAcNumberAndNewPin(long accountNumber,int newPin);
  
  
}
