package com.restapi.expensetracker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Transaction {
	private Integer TransactionId;
	private Integer categoryId;
	private Integer userId;
	private Double amount;
	private String note;
	private long transactionDate;
	
	public Transaction(Integer transactionId, Integer categoryId, Integer userId, Double amount, String note,
			long transactionDate) {
		super();
		TransactionId = transactionId;
		this.categoryId = categoryId;
		this.userId = userId;
		this.amount = amount;
		this.note = note;
		this.transactionDate = transactionDate;
	}

	public Integer getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(Integer transactionId) {
		TransactionId = transactionId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(long transactionDate) {
		this.transactionDate = transactionDate;
	}
}
