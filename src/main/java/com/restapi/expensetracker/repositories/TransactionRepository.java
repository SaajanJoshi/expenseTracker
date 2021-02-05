package com.restapi.expensetracker.repositories;

import java.util.List;

import com.restapi.expensetracker.domain.Transaction;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;

public interface TransactionRepository {
	
	List<Transaction> findAll (Integer userId, Integer categoryId);
	
	Transaction findById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourseNotFoundException;
	
	Integer create(Integer userId, Integer categoryId, Double amount,String note, Long transactionDate) throws EtBadRequestException;
	
	void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction) throws EtBadRequestException;
	
	void removeById(Integer userId, Integer categoryId, Integer transactionId) throws EtResourseNotFoundException;
	
}
