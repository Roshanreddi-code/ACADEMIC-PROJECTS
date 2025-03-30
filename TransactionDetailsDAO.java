package com.bank.DAO;

import java.util.List;

import com.bank.Model.TransactionDetails;

public interface TransactionDetailsDAO
{

	List<TransactionDetails> getTransactionDetailsByCusAcNum(long accountNumber);
	
	void insertTransactionDetails(TransactionDetails transactionsDetails);

}
