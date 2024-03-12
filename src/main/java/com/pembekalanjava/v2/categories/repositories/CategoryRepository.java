package com.pembekalanjava.v2.categories.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pembekalanjava.v2.categories.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	//contoh native query
	@Query(value = "SELECT * FROM categories WHERE id=?1", nativeQuery = true)
	Category getcaCategoryById(Long id);
	
	//contoh pakai param, harus nama di entitites dan tidak select *
	@Query(value = "SELECT c FROM Category as c WHERE id=:id")
	Category getcaCategoryById2(@Param("id") Long id);
}
