package com.restapi.expensetracker.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.restapi.expensetracker.domain.Transaction;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{
	private static final String SQL_CREATE = "INSERT INTO ET_TRANSACTIONS" +  
											 "(TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT,NOTE,TRANSACTION_DATE) " +
											 "VALUES(NEXTVAL('ET_TRANSACTIONS_SEQ'),?,?,?,?,?)";
	
	private static final String SQL_FIND_BY_ID = "SELECT TRANSACTION_ID, CATEGORY_ID, USER_ID, AMOUNT, NOTE, TRANSACTION_DATE " +
												 "FROM ET_TRANSACTIONS WHERE USER_ID = ? AND CATEGORY_ID = ? AND TRANSACTION_ID = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Transaction> findAll(Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction findById(Integer userId, Integer categoryId, Integer transactionId)
			throws EtResourseNotFoundException {
		// TODO Auto-generated method stub
		try {
			return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {userId,categoryId,transactionId},transactionRowMapper);
		} catch (Exception e) {
			throw new EtResourseNotFoundException(e.getMessage());
		}
	}

	@Override
	public Integer create(Integer userId, Integer categoryId, Double amount, String note, Long transactionDate)
			throws EtBadRequestException {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, categoryId);
				ps.setInt(2, userId);
				ps.setDouble(3, amount);
				ps.setString(4, note);
				ps.setLong(5, transactionDate);
				return ps;
			},keyHolder);
			return (Integer) keyHolder.getKeys().get("TRANSACTIOn_ID");
		} catch (Exception e) {
			throw new EtBadRequestException(e.getMessage());
		}
	}

	@Override
	public void update(Integer userId, Integer categoryId, Integer transactionId, Transaction transaction)
			throws EtBadRequestException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeById(Integer userId, Integer categoryId, Integer transactionId)
			throws EtResourseNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
	private RowMapper<Transaction> transactionRowMapper = ((rs,rowNum) ->{
		return new Transaction(
					rs.getInt("USER_ID"),
					rs.getInt("CATEGORY_ID"),
					rs.getInt("TRANSACTION_ID"),
					rs.getDouble("AMOUNT"),
					rs.getString("NOTE"),
					rs.getLong("TRANSACTION_DATE")
				);
	});
}
