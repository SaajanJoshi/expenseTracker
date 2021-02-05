package com.restapi.expensetracker.services;

import java.util.List;

import com.restapi.expensetracker.domain.Category;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;

public interface CategoryService {
	
	List<Category> fetchAllCategories(Integer userId);
	
	Category fetchCategoryById(Integer userId, Integer categoryId) throws EtResourseNotFoundException;
	
	Category addCategory(Integer UserId, String title, String description) throws EtBadRequestException;
	
	void updateCategory(Integer userId,Integer categoryId, Category category) throws EtBadRequestException;
	
	void removeCategoriesWithAllTransactions(Integer userId, Integer categoryId) throws EtResourseNotFoundException;
}
