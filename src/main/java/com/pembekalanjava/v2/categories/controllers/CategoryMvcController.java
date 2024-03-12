package com.pembekalanjava.v2.categories.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pembekalanjava.v2.categories.entities.Category;
import com.pembekalanjava.v2.categories.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryMvcController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("")
	public ModelAndView getAllCategories() {
		ModelAndView view = new ModelAndView("categories/index");
		List<Category> listCategories = categoryService.getAllCategories();
		view.addObject("categoriesData", listCategories);
		return view;
	}
	
	@GetMapping("/form")
	public ModelAndView formAddCategory() {
		ModelAndView view = new ModelAndView("categories/form");
		Category category = new Category();
		view.addObject("categoryForm", category);
		return view;
	}
	
	@PostMapping("/save")
	public ModelAndView saveCategory(@ModelAttribute Category category, BindingResult result) throws Exception {
		if (!result.hasErrors()) {
			try {
				categoryService.saveCategory(category);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ModelAndView("redirect:/categories");
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editCategory(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("categories/form");
		Category category = categoryService.getCategoryById(id);
		view.addObject("categoryForm", category);
		return view;
	}
	
	
	@GetMapping("/delete/{id}")
	public ModelAndView deleteFormCategory(@PathVariable("id") Long id) {
		ModelAndView view = new ModelAndView("categories/form-delete");
		Category category = categoryService.getCategoryById(id);
		view.addObject("categoryForm", category);
		return view;
	}
	
	@GetMapping("/deleteCategory/{id}")
	public ModelAndView deleteCategory(@PathVariable("id") Long id) {
		if (id != null) {
			categoryService.deleteCategoryById(id);
		}
		return new ModelAndView("redirect:/categories");
	}
}
