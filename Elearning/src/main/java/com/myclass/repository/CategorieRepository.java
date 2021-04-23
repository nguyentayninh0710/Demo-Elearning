package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Categories;
import com.myclass.entity.Role;

@Repository
public interface CategorieRepository extends JpaRepository<Categories, Integer> {

	public Role findByTitle(String title);
	public int countByTitle(String title);
}
