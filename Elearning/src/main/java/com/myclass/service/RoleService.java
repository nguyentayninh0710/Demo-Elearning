package com.myclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RoleDto;

public interface RoleService {
	List<RoleDto> getAll();
	RoleDto getById(int id);
	ServiceInfo add(RoleDto dto);
	boolean edit(int id, RoleDto dto);
	void delete(int id);
}
