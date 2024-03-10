package com.pembekalanjava.v2.categories.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pembekalanjava.v2.categories.dtos.request.CategoryRequestDTO;
import com.pembekalanjava.v2.categories.dtos.response.CategoryResponseDTO;
import com.pembekalanjava.v2.categories.entities.Category;
import com.pembekalanjava.v2.categories.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("")
	public ResponseEntity<?> getAllCategory() {
		Map<String, Object> resultMap = new HashMap<>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			List<Category> categories = categoryService.getAllCategories();
			List<CategoryResponseDTO> categoryResponseDTO = categories.stream()
					.map(category -> modelMapper.map(category, CategoryResponseDTO.class))
					.collect(Collectors.toList()); //pemanggilan dengan model mapper library
			resultMap.put("status", "200");
			resultMap.put("message", "Data berhasil ditampilkan");
			resultMap.put("data", categoryResponseDTO);
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", "500");
			resultMap.put("message", "Data gagal ditampilkan");
			
			return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> saveCategory(@RequestBody CategoryRequestDTO categoryRequestDTO) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			Category categoryData = new Category();
			categoryData.setInitial(categoryRequestDTO.getInitial());
			categoryData.setName(categoryRequestDTO.getName());
			categoryData.setDescription(categoryRequestDTO.getDescription());
			categoryData.setIs_delete(false);
			categoryService.createCategory(categoryData);
			
			resultMap.put("status", "200");
			resultMap.put("message", "Data berhasil disimpan");
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", "500");
			resultMap.put("message", "Data gagal disimpan");
			
			return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryRequestDTO categoryRequestDTO, @PathVariable("id") Long id) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus httpStatus = null;
		try {
			Optional<Category> categoryData = categoryService.findCategoryById(id);
			Category categoryDataUpdate = new Category();
			if (categoryData.isPresent()) {
				categoryDataUpdate.setId(id);
				categoryDataUpdate.setInitial(categoryRequestDTO.getInitial());
				categoryDataUpdate.setName(categoryRequestDTO.getName());
				categoryDataUpdate.setDescription(categoryRequestDTO.getDescription());
				categoryDataUpdate.setIs_delete(false);
				categoryDataUpdate.setUpdate_on(new Date());
				categoryService.createCategory(categoryDataUpdate);
				
				resultMap.put("status", "200");
				resultMap.put("message", "Data berhasil diupdate");
				httpStatus = HttpStatus.OK;
			} else {
				resultMap.put("status", "400");
				resultMap.put("message", "Data tidak ditemukan");
				httpStatus = HttpStatus.BAD_REQUEST;
			}
			
			return new ResponseEntity<>(resultMap, httpStatus);
		} catch (Exception e) {
			resultMap.put("status", "500");
			resultMap.put("message", "Data gagal disimpan");
			
			return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
		Map<String, Object> resultMap = new HashMap<>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			Optional<Category> categorie = categoryService.findCategoryById(id);
			
			if (categorie.isPresent()) {
				CategoryResponseDTO categoryResponseDTO = modelMapper.map(categorie, CategoryResponseDTO.class);
				
				resultMap.put("status", "200");
				resultMap.put("message", "Data berhasil ditampilkan");
				resultMap.put("data", categoryResponseDTO);
			} else {
				resultMap.put("status", "400");
				resultMap.put("message", "Data Tidak ditemukan");
			}
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", "500");
			resultMap.put("message", "Data gagal ditampilkan");
			
			return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeCategoryById(@PathVariable("id") Long id) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			categoryService.removeCategory(id);
			resultMap.put("status", "200");
			resultMap.put("message", "Data berhasil dihapus");
			
			return new ResponseEntity<>(resultMap, HttpStatus.OK);
		} catch (Exception e) {
			resultMap.put("status", "500");
			resultMap.put("message", "Data gagal dihapus");
			
			return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
