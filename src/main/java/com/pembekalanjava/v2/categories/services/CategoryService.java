package com.pembekalanjava.v2.categories.services;

import java.util.List;
import java.util.Optional;

import com.pembekalanjava.v2.categories.entities.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	
	void createCategory(Category category);
	
	Optional<Category> findCategoryById(Long id);
	
	void removeCategory(Long id);
	
	
	//dibawh ini untuk mvc
	Category saveCategory(Category category);
	Category getCategoryById(Long id);
	void deleteCategoryById(Long id);
}
