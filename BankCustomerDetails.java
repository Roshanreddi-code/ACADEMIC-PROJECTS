package com.bank.Model;

import java.sql.Date;

public class BankCustomerDetails {
private int id;
private String name;
private String emailId;
private long mobileNumber;
private long aadharNumber;
private String panNumber;
private long accountNumber;
private int pin;
private Date dob;
private  String gender;
private int age;
private String address;
private double amount;
private String status;


public BankCustomerDetails() {
	super();
}


public BankCustomerDetails(int id, String name, String emailId, long mobileNumber, long aadharNumber, String panNumber,
		long accountNumber, int pin, Date dob, String gender, int age, String address, double amount,String status) {
	super();
	this.id = id;
	this.name = name;
	this.emailId = emailId;
	this.mobileNumber = mobileNumber;
	this.aadharNumber = aadharNumber;
	this.panNumber = panNumber;
	this.accountNumber = accountNumber;
	this.pin = pin;
	this.dob = dob;
	this.gender = gender;
	this.age = age;
	this.address = address;
	this.amount = amount;
	this.status=status;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmailId() {
	return emailId;
}


public void setEmailId(String emailId) {
	this.emailId = emailId;
}


public long getMobileNumber() {
	return mobileNumber;
}


public void setMobileNumber(long mobileNumber) {
	this.mobileNumber = mobileNumber;
}


public long getAadharNumber() {
	return aadharNumber;
}


public void setAadharNumber(long aadharNumber) {
	this.aadharNumber = aadharNumber;
}


public String getPanNumber() {
	return panNumber;
}


public void setPanNumber(String panNumber) {
	this.panNumber = panNumber;
}


public long getAccountNumber() {
	return accountNumber;
}


public void setAccountNumber(long accountNumber) {
	this.accountNumber = accountNumber;
}


public int getPin() {
	return pin;
}


public void setPin(int pin) {
	this.pin = pin;
}


public Date getDob() {
	return dob;
}


public void setDob(Date dob) {
	this.dob = dob;
}


public String getGender() {
	return gender;
}


public void setGender(String gender) {
	this.gender = gender;
}


public int getAge() {
	return age;
}


public void setAge(int age) {
	this.age = age;
}


public String getAddress() {
	return address;
}


public void setAddress(String address) {
	this.address = address;
}


public double getAmount() {
	return amount;
}


public void setAmount(double amount) {
	this.amount = amount;
}



public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


@Override
public String toString() {
	return "BankCustomerDetails [id=" + id + ", name=" + name + ", emailId=" + emailId + ", mobileNumber="
			+ mobileNumber + ", aadharNumber=" + aadharNumber + ", panNumber=" + panNumber + ", accountNumber="
			+ accountNumber + ", pin=" + pin + ", dob=" + dob + ", gender=" + gender + ", age=" + age + ", address="
			+ address + ", amount=" + amount + ", status=" + status + "]";
}




}
