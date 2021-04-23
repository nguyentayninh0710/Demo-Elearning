package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Targets;
import com.myclass.entity.User;


@Repository
public interface TargetRepository extends JpaRepository<Targets, Integer>{

	public Targets findByTitle(String title);
	public int countByTitle(String title);
}
