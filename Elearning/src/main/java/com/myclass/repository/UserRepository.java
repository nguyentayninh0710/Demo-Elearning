package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	public int countByEmail(String email);
}
