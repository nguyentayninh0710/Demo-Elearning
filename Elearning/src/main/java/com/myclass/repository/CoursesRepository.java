package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Courses;
import com.myclass.entity.Role;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer>{

	public Role findByTitle(String title);
	public int countByTitle(String title);
}
