package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<RoleDto> getAll() {
		// TODO Auto-generated method stub
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		List<Role> roles = roleRepository.findAll();
		for (Role role : roles) {
			dtos.add(new RoleDto(role.getId(), role.getName(), role.getDescription()));
		}
		return dtos;
	}

	@Override
	public RoleDto getById(int id) {
		Role role = roleRepository.findById(id).get();
		return entityToDto(role);
	}

	private RoleDto entityToDto(Role entity) {
		return new RoleDto(entity.getId(), entity.getName(), entity.getDescription());

	}

	@Override
	public ServiceInfo add(RoleDto dto) {
		// TODO Auto-generated method stub
		int count = roleRepository.countByName(dto.getName());
		if (count > 0)
			return new ServiceInfo(false, "Tên đã sử dụng!");
		try {
			Role role = new Role();
			role.setName(dto.getName());
			role.setDescription(dto.getDescription());
			roleRepository.save(role);
			return new ServiceInfo(true, "Thêm mới thành công!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, RoleDto dto) {
		Optional<Role> optional = roleRepository.findById(id);
		if (optional.isPresent() == false)
			return false;
		Role role = optional.get();
		role.setId(dto.getId());
		role.setName(dto.getName());
		role.setDescription(dto.getDescription());
		return true;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		roleRepository.deleteById(id);
	}

}
