package com.restapi.expensetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restapi.expensetracker.domain.Category;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;
import com.restapi.expensetracker.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> fetchAllCategories(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourseNotFoundException {
		// TODO Auto-generated method stub
		return categoryRepository.findById(userId, categoryId);
	}

	@Override
	public Category addCategory(Integer UserId, String title, String description) throws EtBadRequestException {
		// TODO Auto-generated method stub
		int categoryId = categoryRepository.create(UserId, title, description);
		return categoryRepository.findById(UserId, categoryId);
	}

	@Override
	public void updateCategory(Integer userId, Integer categoryId, Category category) throws EtBadRequestException {
		// TODO Auto-generated method stub
		categoryRepository.update(userId, categoryId, category);
	}

	@Override
	public void removeCategoriesWithAllTransactions(Integer userId, Integer categoryId)
			throws EtResourseNotFoundException {
		// TODO Auto-generated method stub
		
	}


}
