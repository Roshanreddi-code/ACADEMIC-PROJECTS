package com.bank.Services;

import com.bank.Model.BankCustomerDetails;

public interface AdminService {
  public void adminLogin();
  
  void allUserDetails();
  
  void allPendingDetails();
  
  void acceptPendingDetails(BankCustomerDetails bankCustomerDetails);
  
}
