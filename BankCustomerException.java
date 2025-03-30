package com.bank.Bank;

public class BankCustomerException extends RuntimeException{
 private String str;

public BankCustomerException(String str) {
	super();
	this.str = str;
}

public String getExceptionMsg() {
	return str;
}

public void setExceptionMsg(String str) {
	this.str = str;
}
 
}
