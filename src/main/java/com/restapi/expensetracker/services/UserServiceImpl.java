package com.restapi.expensetracker.services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.expensetracker.domain.User;
import com.restapi.expensetracker.exceptions.EtAuthException;
import com.restapi.expensetracker.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User validateUser(String email,String password) throws EtAuthException{
		if (email != null) email = email.toLowerCase();
		User user = userRepository.findByEmailAndPassword(email, password);
		return user;
	} 
	
	@Override 
	public User registerUser(String firstName, String lastName, String email,String password) throws EtAuthException{
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		if (email != null) email = email.toLowerCase();
		if (!pattern.matcher(email).matches())
			throw new EtAuthException("Invalid email format");
		Integer count = userRepository.getCountByEmail(email);
		if (count > 0)
			throw new EtAuthException("Email already exists");
		Integer userId = userRepository.create(firstName, lastName, email, password);
		return userRepository.findById(userId);
	}
}
