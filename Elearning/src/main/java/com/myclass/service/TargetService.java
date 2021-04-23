package com.myclass.service;

import java.util.List;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.TargetsDto;

public interface TargetService {

	List<TargetsDto> getAll();
	TargetsDto getById(int id);
	ServiceInfo add(TargetsDto dto);
	boolean edit(int id, TargetsDto dto);
	void delete(int id);
}
