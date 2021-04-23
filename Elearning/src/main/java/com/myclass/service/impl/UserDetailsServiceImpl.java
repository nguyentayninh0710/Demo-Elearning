package com.myclass.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myclass.dto.UserDetailsDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Role;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;
import com.myclass.until.CustomUserDetails;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//gọi hàm để kiểm tra
		User user = userRepository.findByEmail(email);
		
		
		if(user == null) {
			throw new UsernameNotFoundException("Không tìm thấy tài khoản");
		}
		//Tạo đối tượng kiểu Userdetails  và lưu thông tin user vào đóng
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Role role = user.getMtoRole();
		authorities.add(new SimpleGrantedAuthority(user.getRoleName()));
		
		CustomUserDetails userDetails = new CustomUserDetails(user.getFullname(), 
				user.getPassword(), authorities);
		userDetails.setFullname(user.getFullname());
		
		//trả về 
		return userDetails;
	}

}
