package com.bank.DAO;

public interface AdminDAO {
	
  public boolean selectAdminDetailsByUsingEmailIdAndPassword(String emailId,String password);
}
