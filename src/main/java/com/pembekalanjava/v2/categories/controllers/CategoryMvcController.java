package com.pembekalanjava.v2.categories.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
