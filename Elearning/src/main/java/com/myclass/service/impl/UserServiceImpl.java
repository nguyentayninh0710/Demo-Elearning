package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.RoleRepository;
import com.myclass.repository.UserRepository;
import com.myclass.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserDto> getAll() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userRepository.findAll();
		for (User user : users) {
			dtos.add(new UserDto(user.getId(), user.getEmail(), user.getFullname(), user.getAvatar(), user.getPhone(),
					user.getAddress(), user.getRoleName()));
		}
		return dtos;
	}

	@Override
	public UserDto getById(int id) {
		User user = userRepository.findById(id).get();
		return entityToDto(user);
	}

	public UserDto entityToDto(User entity) {
		return new UserDto(entity.getId(), 
				entity.getEmail(), 
				entity.getFullname(), 
				entity.getPassword(),
				entity.getAvatar(), 
				entity.getPhone(), 
				entity.getAddress(), 
				entity.getRoleId(), 
				entity.getRoleName());
	}

	@Override
	public ServiceInfo add(UserDto dto) {
		int count = userRepository.countByEmail(dto.getEmail());
		if (count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			// nếu chưa thì thêm mới
			User user = new User();
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(12));
			user.setId(dto.getId());
			user.setEmail(dto.getEmail());
			user.setFullname(dto.getFullname());
			user.setAvatar(dto.getAvatar());
			user.setPassword(hashed);
			user.setPhone(dto.getPhone());
			user.setAddress(dto.getAddress());
			user.setRoleId(dto.getRoleId());
			List<Role> roles = roleRepository.findAll();
			for (Role role : roles) {
				if (role.getId() == dto.getRoleId())
					user.setRoleName(role.getName());
			}
			userRepository.save(user);
			return new ServiceInfo(true, "Thêm mới thành công!", user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, UserDto dto) {
		System.out.println("id="+id);
		Optional<User> optional = userRepository.findById(id);
		if (optional.isPresent() == false) return false;
		User user = optional.get();
		user.setId(dto.getId());
		user.setEmail(dto.getEmail());
		user.setFullname(dto.getFullname());
		user.setAvatar(dto.getAvatar());
		user.setPhone(dto.getPhone());
		user.setAddress(dto.getAddress());
		user.setRoleId(dto.getRoleId());
		List<Role> roles = roleRepository.findAll();
		for (Role role : roles) {
			if (role.getId() == dto.getRoleId())
				user.setRoleName(role.getName());
		}
		userRepository.save(user);
		return true;
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);

	}

}
