package com.in.main.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.in.main.dao.CategoryRepo;
import com.in.main.entity.Category;


@Service
public class CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	public Iterable<Category> getAllCategory(int page) {
		return categoryRepo.findAll(PageRequest.of(page, 10));
	}

	public Category createCategory(Category category) {
		return categoryRepo.save(category);
	}


	public Optional<Category> getCategoryById(Integer categoryId ) {
		return categoryRepo.findById(categoryId);
	}


	public Object updateCategoryById(Integer categoryId, Category category) {

		Optional<Category> existCategory = categoryRepo.findById(categoryId);
		if (existCategory.isPresent()) {
			category.setId(categoryId);
			return categoryRepo.save(category);
		}
		return null;
	}


	public boolean deleteCategory(Integer categoryId) {
		if (categoryRepo.existsById(categoryId)) {
			categoryRepo.deleteById(categoryId);
			return true;
		}
		return false;
	}


}
