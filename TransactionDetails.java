package com.bank.Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {

	    private int transactionId;
	    private String transactionType;
	    private LocalDate transactionDate;
	    private LocalTime transactionTime;
	    private double balanceAmount;
	    private double transationAmount;
	    private long accountNumber;
	    public TransactionDetails(){
	    	
	    }
		public TransactionDetails(int transactionId, String transactionType, LocalDate transactionDate,
				LocalTime transactionTime, double balanceAmount, double transationAmount, long accountNumber) {
			super();
			this.transactionId = transactionId;
			this.transactionType = transactionType;
			this.transactionDate = transactionDate;
			this.transactionTime = transactionTime;
			this.balanceAmount = balanceAmount;
			this.transationAmount = transationAmount;
			this.accountNumber = accountNumber;
		}
		public int getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(int transactionId) {
			this.transactionId = transactionId;
		}
		public  String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public LocalDate getTransactionDate() {
			return transactionDate;
		}
		public void setTransactionDate(LocalDate transactionDate) {
			this.transactionDate = transactionDate;
		}
		public LocalTime getTransactionTime() {
			return transactionTime;
		}
		public void setTransactionTime(LocalTime transactionTime) {
			this.transactionTime = transactionTime;
		}
		public double getBalanceAmount() {
			return balanceAmount;
		}
		public void setBalanceAmount(double balanceAmount) {
			this.balanceAmount = balanceAmount;
		}
		public double getTransationAmount() {
			return transationAmount;
		}
		public void setTransationAmount(double transationAmount) {
			this.transationAmount = transationAmount;
		}
		public long getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
		}
		@Override
		public String toString() {
			return "TransactionDetails [transactionId=" + transactionId + ", transactionType=" + transactionType
					+ ", transactionDate=" + transactionDate + ", transactionTime=" + transactionTime + ", balanceAmount="
					+ balanceAmount + ", transationAmount=" + transationAmount + ", accountNumber=" + accountNumber + "]";
		}
	}
