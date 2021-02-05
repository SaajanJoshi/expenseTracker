package com.restapi.expensetracker.repositories;

import java.util.List;

import com.restapi.expensetracker.domain.Category;
import com.restapi.expensetracker.exceptions.EtBadRequestException;
import com.restapi.expensetracker.exceptions.EtResourseNotFoundException;

public interface CategoryRepository {

		List<Category> findAll(Integer userId) throws EtResourseNotFoundException;
		
		Category findById(Integer userId, Integer cartegoryId) throws EtBadRequestException;
		
		Integer create(Integer userId, String title, String description) throws EtBadRequestException;
		
		void update(Integer userId, Integer categoryId, Category category) throws EtBadRequestException;
		
		void removeById(Integer userId, Integer CategoryId);
}
