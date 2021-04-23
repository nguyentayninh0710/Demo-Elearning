package com.myclass.dto;

import java.beans.Transient;

public class UserDto {
	private int id;
	private String email;
	private String fullname;
	private String password;
	private String avatar;
	private String phone;
	private String address;
	private int roleId;
	private String roleName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDto() {
		super();
	}
	public UserDto(int id, String email, String fullname, String password, String avatar, String phone, String address,
			int roleId, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleName = roleName;
	}
	public UserDto(int id, String email, String fullname, String avatar, String phone, String address,
			String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.fullname = fullname;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public UserDto(String email, String fullname, String password, String avatar, String phone, String address,
			int roleId, String roleName) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.password = password;
		this.avatar = avatar;
		this.phone = phone;
		this.address = address;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	
	@Transient
	public String getImgPath() {
		if(avatar==null || id ==0)
			return null;
		return "/user-photos/"+id+"/"+avatar;
	}
	

	
}
