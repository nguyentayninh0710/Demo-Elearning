package com.myclass.service;

import java.util.List;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.UserDto;

public interface UserService {
	List<UserDto> getAll();
	UserDto getById(int id);
	ServiceInfo add(UserDto dto);
	boolean edit(int id, UserDto dto);
	void delete(int id);
	
}
