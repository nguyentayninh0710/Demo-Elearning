package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CategoriesDto;;


public interface CategorieService {

	List<CategoriesDto> getAll();
	CategoriesDto getById(int id);
	ServiceInfo add(CategoriesDto dto);
	boolean edit(int id, CategoriesDto dto);
	void delete(int id);
}
