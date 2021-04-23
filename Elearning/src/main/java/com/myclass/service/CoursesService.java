package com.myclass.service;

import java.util.List;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CoursesDto;;

public interface CoursesService {
	List<CoursesDto> getAll();
	CoursesDto getById(int id);
	ServiceInfo add(CoursesDto dto);
	boolean edit(int id, CoursesDto dto);
	void delete(int id);
}
