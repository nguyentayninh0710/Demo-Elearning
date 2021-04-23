package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myclass.entity.Targets;
import com.myclass.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer>{

	public Video findByTitle(String title);
	public int countByTitle(String title);
}
