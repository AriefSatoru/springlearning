package com.pembekalanjava.v2.categories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pembekalanjava.v2.categories.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
