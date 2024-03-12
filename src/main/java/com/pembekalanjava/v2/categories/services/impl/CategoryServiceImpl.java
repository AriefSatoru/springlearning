package com.pembekalanjava.v2.categories.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pembekalanjava.v2.categories.entities.Category;
import com.pembekalanjava.v2.categories.repositories.CategoryRepository;
import com.pembekalanjava.v2.categories.services.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public void createCategory(Category category) {
		categoryRepository.save(category);
	}

	@Override
	public Optional<Category> findCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public void removeCategory(Long id) {
		categoryRepository.deleteById(id);
	}
	
	//dibawah ini untuk yg mvc

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.getcaCategoryById2(id);
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}

}
