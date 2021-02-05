package com.restapi.expensetracker.services;

import java.util.List;

import com.restapi.expensetracker.domain.Transaction;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;

public interface TransactionService {
	List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId);
	
	Transaction fetchTransactionById(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;
	
	Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate) throws EtBadRequestException;
	
	void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
	
	void removeTransaction(Integer userId, Integer categoryId, Integer transactionId) throws EtResourseNotFoundException;
}
