package com.restapi.expensetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.expensetracker.domain.Transaction;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;
import com.restapi.expensetracker.repositories.CategoryRepository;
import com.restapi.expensetracker.repositories.TransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{
	@Autowired
	TransactionRepository transactionRepository;
	
	@Override
	public List<Transaction> fetchAllTransactions(Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Transaction addTransaction(Integer userId, Integer categoryId, Double amount, String note,
			Long transactionDate) throws EtBadRequestException {
		// TODO Auto-generated method stub
		int transactionId = transactionRepository.create(userId, categoryId, amount, note, transactionDate);
		return transactionRepository.findById(userId, categoryId, transactionId);
	}


	@Override
	public Transaction fetchTransactionById(Integer userId, Integer categoryId, Double amount, String note,
			Long transactionDate) throws EtBadRequestException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTransaction(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
			throws EtBadRequestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTransaction(Integer userId, Integer categoryId, Integer transactionId)
			throws EtResourseNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
