package com.restapi.expensetracker.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.restapi.expensetracker.domain.User;
import com.restapi.expensetracker.exceptions.EtAuthException;

@Repository
public class UserRepositoryImpl implements UserRepository{
	private static final String SQL_CREATE = "INSERT INTO ET_USERS(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD) VALUES(NEXTVAL('ET_USERS_SEQ'),?,?,?,?)";
	private static final String SQL_COUND_BY_EMAIl = "SELECT COUNT(*) FROM ET_USERS WHERE EMAIL = ?";
	private static final String SQL_FIND_BY_ID = "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD FROM ET_USERS WHERE USER_ID = ?";
	private static final String SQL_FIND_BY_EMAIL = "SELECT USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PASSWORD FROM ET_USERS WHERE EMAIL = ?";
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Integer create(String firstName, String lastName, String email, String password) throws EtAuthException {
		// TODO Auto-generated method stub
		String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
		try{
				KeyHolder keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(connection -> {
					PreparedStatement ps = connection.prepareStatement(SQL_CREATE,Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, firstName);
					ps.setString(2, lastName);
					ps.setString(3, email);
					ps.setString(4, hashedPassword);
					return ps;
				},keyHolder);
				return (Integer) keyHolder.getKeys().get("USER_ID");
		} catch (Exception e) {
			throw new EtAuthException ("Invalid detail, failed to create account");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public User findByEmailAndPassword(String email, String password) throws EtAuthException {
		// TODO Auto-generated method stub
		try {
			User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[] {email}, userRowMapper);
			if (!BCrypt.checkpw(password, user.getPassword()))
				throw new EtAuthException("Invalid email/password");
			return user;
			
		} catch (Exception e) { 
			throw new EtAuthException("Invalid email/password");
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public Integer getCountByEmail(String email) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_COUND_BY_EMAIl, new Object[] {email},Integer.class);

	}

	@SuppressWarnings("deprecation")
	@Override
	public User findById(Integer userId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[] {userId},userRowMapper);
	}
	
	private RowMapper<User> userRowMapper = ((rs,rowNum)->{
		return new User(rs.getInt("USER_ID"),
						rs.getString("FIRST_NAME"),
						rs.getString("LAST_NAME"),
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"));
	});
}
